package com.loki.Login.model.dao;

import lombok.Data;

/**
 * 注册dao
 *
 * @author Arthurocky
 */
@Data
public class UserRegisterDao {
    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 确定密码
     */
    private String checkPassword;

    /**
     * 用户编号
     */
    private String userCode;
}
