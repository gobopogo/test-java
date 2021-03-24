package com.misslyr.test.proxy;

import com.misslyr.test.proxy.service.*;
import com.misslyr.test.proxy.service.impl.DynamicProxy;
import com.misslyr.test.proxy.service.impl.StaticProxy;
import com.misslyr.test.proxy.service.impl.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author missli
 * @Description java 动态代理示例
 * Spring 的 AOP 也都是动态代理实现的
 * @Date 2021/3/19 10:44
 **/
public class ProxyMain {
    public static void main(String[] args) {
        final UserService target = new UserServiceImpl();
        System.out.println("=======================================================");
        UserService targetProxy = (UserService) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(),
                new Class[]{UserService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("前置逻辑");
                        try{
                            return method.invoke(target, args);
                        }finally {
                            System.out.println("后置逻辑");
                        }
                    }
                });
        System.out.println("代理对象调用被代理函数返回值：" + targetProxy.getName("111111"));

        /**
         * 静态代理会为每一个业务增强都提供一个代理类, 由代理类来创建代理对象
         */
        System.out.println("=======================================================");
        StaticProxy staticProxy = new StaticProxy(target);
        System.out.println("静态代理对象调用被代理函数返回值" + staticProxy.getName("静静"));


        /**
         * 动态代理并不存在代理类, 代理对象直接由代理生成工具动态生成.
         */
        System.out.println("=======================================================");
        UserService targetProxy1 = (UserService) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(),
                target.getClass().getInterfaces(), new DynamicProxy(target));
        System.out.println("代理(封装)对象调用被代理函数返回值：" + targetProxy1.getName("5555"));

        /**
         * JDK动态代理必须要有接口, 但如果要代理一个没有接口的类该怎么办呢?
         * 这时我们可以使用CGLIB动态代理. CGLIB动态代理的原理是生成目标类的子类, 这个子类对象就是代理对象, 代理对象是被增强过的.
         */
        System.out.println("=======================================================");
        FruitService fruitService = new FruitService();
        //创建代理对象
        FruitService proxy = (FruitService) Enhancer.create(fruitService.getClass(), new CgLibProxy());
        System.out.println("cglib代理对象调用被代理函数返回值：" + proxy.getFruit("苹果"));

        /**
         * 模拟Spring AOP场景
         */
        System.out.println("=======================================================");
        //切面
        BaseAspect baseAspect = new BaseAspect();
        //创建代理对象
        UserService proxy2 = ProxyFactory.createProxy(target.getClass(), baseAspect);
        proxy2.getName("6666");

    }
}
