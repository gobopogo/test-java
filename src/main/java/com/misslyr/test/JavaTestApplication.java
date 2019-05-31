package com.misslyr.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author missli
 * @Description
 * @Date 2019/5/31 14:36
 **/

@SpringBootApplication
@EnableCaching
public class JavaTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaTestApplication.class);
    }
}
