package com.misslyr.test.proxy.service.impl;

import com.misslyr.test.proxy.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author missli
 * @Description动态代理
 * @Date 2021/3/19 11:23
 **/
public class DynamicProxy implements InvocationHandler {

    private UserService userService;

    public DynamicProxy (UserService userService){
        this.userService = userService;
    }

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        return method.invoke(userService, args);
    }

    private void before(){
        System.out.println("代理(封装) 前置逻辑");
    }
}
