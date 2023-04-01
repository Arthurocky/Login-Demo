package com.loki.Login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.Login.mapper.UserMapper;
import com.loki.Login.model.User;
import com.loki.Login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.loki.Login.common.Basecontant.*;

/**
 * @author Arthurocky
 * @description 针对表【user(用户表创建)】的数据库操作Service实现
 * @createDate 2023-03-05 23:07:40
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * 注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param userCode      星球编号
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String userCode)
    {
        //校验是否为空
        /*if (StringUtils.isEmpty(userAccount)||StringUtils.isEmpty(userPassword)||StringUtils.isEmpty(checkPassword)||StringUtils.isEmpty(planetCode)){
        }*/
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, userCode)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }

        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }

        if (!userPassword.equals(checkPassword)) {
            return -1;
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserCode, userCode);
        User user = this.getOne(wrapper);
        /*int count = this.count(wrapper);
        if (count == 0) {*/
        if (user != null) {
            return -1;
        }

        //加密
        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        System.out.println(newPassword);

        //插入数据
        User user1 = new User();
        user1.setUserAccount(userAccount);
        user1.setUserPassword(newPassword);
        boolean save = this.save(user1);
        if (!save) {
            return -1;
        }
        return user1.getId();
    }


    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request)
    {
        //校验是否为空
        /*if (StringUtils.isEmpty(userAccount)||StringUtils.isEmpty(userPassword)||StringUtils.isEmpty(checkPassword)||StringUtils.isEmpty(planetCode)){
        }*/
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }

        if (userPassword.length() < 8) {
            return null;
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }

        //加密
        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //用户是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserAccount, userAccount);
        wrapper.eq(User::getUserPassword, newPassword);
        User user = this.getOne(wrapper);
        if (user == null) {
            log.info("login error: userAccount cannot be find");
            return null;
        }

        //脱敏
        User safetyUser = getSafetyUser(user);
        //记录状态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;

    }


    /**
     * 用户脱敏
     *
     * @param originUser 对象
     * @return 脱敏后的对象
     */
    @Override
    public User getSafetyUser(User originUser)
    {
        if (originUser == null) {
            return null;
        }
        User newUser = new User();
        //设置返回的数据
        newUser.setId(newUser.getId());
        newUser.setUsername(newUser.getUsername());
        newUser.setUserAccount(newUser.getUserAccount());
        newUser.setAvatarUrl(newUser.getAvatarUrl());

        newUser.setGender(newUser.getGender());
        newUser.setPhone(newUser.getPhone());
        newUser.setEmail(newUser.getEmail());
        newUser.setUserCode(newUser.getUserCode());
        newUser.setUserRole(newUser.getUserRole());

        newUser.setUserStatus(newUser.getUserStatus());
        newUser.setCreateTime(newUser.getCreateTime());
        return newUser;
    }

    @Override
    public List<User> searchUser(String name, HttpServletRequest request)
    {
        //仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (null == user || !user.getUserRole().equals(ADMIN_ROLE)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getUsername, name);
        List<User> users = this.list();
        ArrayList<User> userArrayList = new ArrayList<>();
        for (User user1 : users) {
            this.getSafetyUser(user1);
            user.setUserPassword(null);
            userArrayList.add(user1);
        }
        return userArrayList;
    }

    @Override
    public Boolean deleteUser(long id ,HttpServletRequest request)
    {
        //仅管理员可删除
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (null == user || !user.getUserRole().equals(ADMIN_ROLE)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return this.removeById(id);
    }

}




