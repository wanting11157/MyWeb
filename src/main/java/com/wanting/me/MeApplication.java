package com.wanting.me;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wanting.me.mapper")
public class MeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeApplication.class, args);
    }

}
