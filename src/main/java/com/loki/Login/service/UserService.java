package com.loki.Login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loki.Login.model.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

/**
* @author Arthurocky
* @description 针对表【user(用户表创建)】的数据库操作Service
* @createDate 2023-03-05 23:07:40
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param userCode 用户编号
     * @return 新用户 id
     */
    long userRegister(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword, @Param("checkPassword") String checkPassword, @Param("userCode") String userCode);


    /**
     * 用户登录
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);


}
