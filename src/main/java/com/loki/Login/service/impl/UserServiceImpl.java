package com.loki.Login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.Login.common.ErrorCode;
import com.loki.Login.common.Result;
import com.loki.Login.exception.BusinessException;
import com.loki.Login.mapper.UserMapper;
import com.loki.Login.model.User;
import com.loki.Login.service.UserService;
import com.loki.Login.utils.R;
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
    public Result<Long> userRegister(String userAccount, String userPassword, String checkPassword, String userCode)
    {
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, userCode)) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不符合条件");
        }
        if (userAccount.length() < 4) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不符合条件");
        }

        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不符合条件");
        }

        if (userCode.length() < 1) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不符合条件");
        }

        if (!userPassword.equals(checkPassword)) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不符合条件");
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不符合条件");
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserCode, userCode);
        User user = this.getOne(wrapper);
        if (user != null) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.NULL_ERROR,"获取的数据为空");
        }

        LambdaQueryWrapper<User> wrapper2 = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserAccount, userAccount);
        long count = this.count(wrapper2);
        if (count > 1) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"获取的对象错误");
        }

        //加密
        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        System.out.println(newPassword);

        //插入数据
        User user1 = new User();
        user1.setUserAccount(userAccount);
        user1.setUserPassword(newPassword);
        user1.setUserAccount(userCode);
        boolean save = this.save(user1);
        if (!save) {
            //return R.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"保存失败");
        }
        return R.success(user1.getId());
    }


    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request      请求
     * @return 脱敏后的用户信息
     */
    @Override
    public Result<User> userLogin(String userAccount, String userPassword, HttpServletRequest request)
    {
        //校验是否为空
        /*if (StringUtils.isEmpty(userAccount)||StringUtils.isEmpty(userPassword)||StringUtils.isEmpty(checkPassword)||StringUtils.isEmpty(planetCode)){
        }*/
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            //return R.error(ErrorCode.NULL_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数错误");
        }
        if (userAccount.length() < 4) {
            //return R.error(ErrorCode.NULL_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数错误");
        }

        if (userPassword.length() < 8) {
            //return R.error(ErrorCode.NULL_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数错误");
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            //return R.error(ErrorCode.NULL_ERROR);
            throw new BusinessException(ErrorCode.NULL_ERROR,"请求参数错误");
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
            throw new BusinessException(ErrorCode.NULL_ERROR,"请求对象不存在");
        }

        //脱敏
        User safetyUser = getSafetyUser(user);
        //记录状态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return R.success(safetyUser);

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
            throw new BusinessException(ErrorCode.NULL_ERROR,"请求对象不存在");
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
    public Result<List<User>> searchUser(String name, HttpServletRequest request)
    {
        //仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (null == user || !user.getUserRole().equals(ADMIN_ROLE)) {
            //return R.error(ErrorCode.SYSTEM_ERROR);
            throw new BusinessException(ErrorCode.NOT_LOGIN,"权限不足");
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
        return R.success(userArrayList);
    }

    @Override
    public Result<Boolean> deleteUser(long id, HttpServletRequest request)
    {
        //仅管理员可删除
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (null == user || !user.getUserRole().equals(ADMIN_ROLE)) {
            //return R.error(ErrorCode.NO_AUTH);
            throw new BusinessException(ErrorCode.NOT_LOGIN,"权限不足");
        }
        if (id <= 0) {
            //return R.error(ErrorCode.NO_AUTH);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"对象不存在");
        }
        return R.success(this.removeById(id));
    }

    /**
     * 用户注销
     *
     * @param request 请求
     * @return {@link Integer}
     */
    @Override
    public Result<Integer> userLogout(HttpServletRequest request)
    {
        //移除登录
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return R.success(null);
    }


    /**
     * 获取当前用户
     *
     * @param request 请求
     * @return {@link User}
     */
    @Override
    public Result<User> getCurrentUser(HttpServletRequest request)
    {
        if (request == null) {
            return null;
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            //return null;
            throw new BusinessException(ErrorCode.NULL_ERROR,"对象不存在");
        }
        Long userId = currentUser.getId();
        User user = this.getById(userId);
        return R.success(this.getSafetyUser(user));

    }

}




