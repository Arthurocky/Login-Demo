package com.loki.Login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.Login.mapper.UserMapper;
import com.loki.Login.model.User;
import com.loki.Login.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Arthurocky
 * @description 针对表【user(用户表创建)】的数据库操作Service实现
 * @createDate 2023-03-05 23:07:40
 */
@Service
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
        int count = this.count(wrapper);
        if (count == 0) {
            return -1;
        }

        //加密
        final String SALT = "SALT+LOKI";
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
}




