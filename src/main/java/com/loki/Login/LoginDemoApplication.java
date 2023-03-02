package com.loki.Login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com/loki/Login/mapper")
public class LoginDemoApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(LoginDemoApplication.class, args);
    }

}
