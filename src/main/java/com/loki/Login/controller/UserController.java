package com.loki.Login.controller;

import com.loki.Login.model.dao.UserRegisterDao;
import com.loki.Login.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public Long userRegister(@RequestBody UserRegisterDao userRegisterDao)
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
        long register = userService.userRegister(userAccount, userPassword, checkPassword, userCode);
        return register;
    }
}
