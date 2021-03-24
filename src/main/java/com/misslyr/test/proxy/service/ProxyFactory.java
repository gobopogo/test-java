package com.misslyr.test.proxy.service;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @Author missli
 * @Description
 * @Date 2021/3/19 17:26
 **/
public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final  Class<T> targetClass, final MethodInterceptor methodInterceptor){
        return (T) Enhancer.create(targetClass, methodInterceptor);
    }
}
