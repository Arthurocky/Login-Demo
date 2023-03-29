package com.loki.Login.service;

import com.loki.Login.mapper.UserMapper;
import com.loki.Login.model.User;
import com.loki.Login.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务测试
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 测试Mybatis是否成功注入
     */
    //@Test
    public void testSelectUser() {
        System.out.println("----selectAll method test");
        List<User> users= userMapper.selectList(null);
        //下断言:已设定初始化用户数为5，如果不无5这说明有误
        Assert.assertEquals(5,users.size());
        /*for (User user : users) {
            System.out.println(user);
        }*/
        users.forEach(System.out::println);
    }

    @Resource
    private UserService userService;

    @Test
    void userRegister() {
        String userAccount = "loki";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        String planetCode = "2";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "lk";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "loki";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "lo/ki";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "arthurocky";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "loki";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
    }
}