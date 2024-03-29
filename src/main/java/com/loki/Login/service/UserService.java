package com.loki.Login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loki.Login.common.Result;
import com.loki.Login.model.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    Result<Long> userRegister(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword, @Param("checkPassword") String checkPassword, @Param("userCode") String userCode);


    /**
     * 用户登录
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    Result<User> userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 用户脱敏
     * @param originUser
     * @return 用户
     */
    User getSafetyUser(User originUser);


    /**
     * 搜索用户
     *
     * @param name    名字
     * @param request 请求
     * @return 用户列表
     */
    Result<List<User>> searchUser(String name, HttpServletRequest request);


    /**
     * 删除
     *
     * @param id      id
     * @param request 请求
     * @return {@link Boolean}
     */
    Result<Boolean> deleteUser(long id , HttpServletRequest request);


    /**
     * 用户注销
     *
     * @param request 请求
     * @return {@link Integer}
     */
    Result<Integer> userLogout(HttpServletRequest request);

    /**
     * 获取当前用户
     *
     * @param request 请求
     * @return {@link User}
     */
    Result<User> getCurrentUser(HttpServletRequest request);
}
