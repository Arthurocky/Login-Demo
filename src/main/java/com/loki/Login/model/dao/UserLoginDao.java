package com.loki.Login.model.dao;

import lombok.Data;

/**
 * 登录dao
 *
 * @author Arthurocky
 */
@Data
public class UserLoginDao {

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     *密码
     */
    private String userPassword;

}
