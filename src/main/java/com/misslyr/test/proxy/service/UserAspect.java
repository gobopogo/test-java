package com.misslyr.test.proxy.service;

import java.lang.reflect.Method;

/**
 * @Author missli
 * @Description 创建一个切面类, 类中配置切入点和增强.
 * @Date 2021/3/19 17:23
 **/
public class UserAspect extends BaseAspect {

    /**
     * 切入点
     */
    public boolean isIntercept(Method method, Object[] args) throws Throwable {
        return method.getName().equals("getFruit");
    }

    /**
     * 前置增强
     */
    public void before() throws Throwable {
        System.out.println("模拟Spring AOP 执行前置逻辑");
    }
}
