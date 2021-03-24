package com.misslyr.test.proxy.service.impl;

import com.misslyr.test.proxy.service.UserService;

/**
 * @Author missli
 * @Description 静态代理模式
 * @Date 2021/3/19 11:12
 **/
public class StaticProxy implements UserService {

    private UserService userService; //被代理对象

    //构造函数
    public StaticProxy(UserService userService){
        this.userService = userService;
    }

    @Override
    public String getName(String userId) {
        this.before();
        return userService.getName(userId);
    }

    private void before(){
        System.out.println("静态代理 前置逻辑");
    }
}
