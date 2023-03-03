package com.loki.Login.service;

import com.loki.Login.bean.User;
import com.loki.Login.mapper.UserMapper;
import org.junit.Assert;
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
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 测试Mybatis是否成功注入
     */
    @Test
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

}