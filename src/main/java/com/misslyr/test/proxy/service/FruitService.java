package com.misslyr.test.proxy.service;

/**
 * @Author missli
 * @Description
 * @Date 2021/3/19 11:39
 **/
public class FruitService {

    public String getFruit(String name){
        System.out.println("执行业务逻辑");
        return name;
    }

}
