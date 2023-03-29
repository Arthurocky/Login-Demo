package com.loki.Login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.Login.model.User;
import com.loki.Login.service.UserService;
import com.loki.Login.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Arthurocky
* @description 针对表【user(用户表创建)】的数据库操作Service实现
* @createDate 2023-03-05 23:07:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




