package com.misslyr.test.proxy.service.impl;

import com.misslyr.test.proxy.service.UserService;

/**
 * @Author missli
 * @Description 被代理接口
 * @Date 2021/3/19 10:42
 **/
public class UserServiceImpl implements UserService {
    @Override
    public String getName(String userId) {
        System.out.println("执行业务方法");
        return "hello" + userId;
    }
}
