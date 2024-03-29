package com.misslyr.test.stategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)  //注解类型，用在类，方法，字段...
@Retention(RetentionPolicy.RUNTIME)  //生命周期
public @interface PayWay {
    int value(); //参数
}
