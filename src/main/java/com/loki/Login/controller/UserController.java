package com.loki.Login.controller;

import com.loki.Login.common.ErrorCode;
import com.loki.Login.common.Result;
import com.loki.Login.exception.BusinessException;
import com.loki.Login.model.User;
import com.loki.Login.model.dao.UserLoginDao;
import com.loki.Login.model.dao.UserRegisterDao;
import com.loki.Login.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口
 *
 * @author Arthurocky <br/>
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<Long> userRegister(@RequestBody UserRegisterDao userRegisterDao)
    {
        if (userRegisterDao == null) {
            return null;
        }
        String userAccount = userRegisterDao.getUserAccount();
        String userPassword = userRegisterDao.getUserPassword();
        String checkPassword = userRegisterDao.getCheckPassword();
        String userCode = userRegisterDao.getUserCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, userCode)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword, userCode);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<User> userLogin(@RequestBody UserLoginDao userLoginDao, HttpServletRequest request)
    {
        if (userLoginDao == null) {
            return null;
        }
        String userAccount = userLoginDao.getUserAccount();
        String userPassword = userLoginDao.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }


    /**
     * 搜索
     *
     * @param name 名字
     * @return {@link List}<{@link User}>
     */
    @GetMapping("/search")
    public Result<List<User>> search(String name , HttpServletRequest request){
        return userService.searchUser(name, request);
    }


    /**
     * 删除用户
     *
     * @param id id
     * @return {@link Boolean}
     */
    @DeleteMapping("/delete")
    public Result<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        return userService.deleteUser(id, request);
    }


    /**
     * 用户注销
     *
     *
     * @param request 请求
     * @return int
     */
    @PostMapping("/logout")
    public Result<Integer> userLogout(HttpServletRequest request)
    {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return userService.userLogout(request);
    }


    /**
     * 你好连接测试
     *
     * @return {@link String}
     */
    @PostMapping("/hello")
    public String hello()
    {
        return "Hello";
    }
}
