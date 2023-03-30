package com.loki.Login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Arthurocky
 */
@SpringBootApplication
@MapperScan(basePackages = "com/loki/Login/mapper")
public class LoginDemoApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(LoginDemoApplication.class, args);
    }

}
