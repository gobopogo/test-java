package com.misslyr.test.proxy.service;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author missli
 * @Description CgLib 动态代理
 * @Date 2021/3/19 11:41
 **/
public class CgLibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        //return method.invoke(o, objects);
        return methodProxy.invokeSuper(o, objects);  //也可以
    }

    private void before(){
        System.out.println("CgLib代理 前置逻辑");
    }
}