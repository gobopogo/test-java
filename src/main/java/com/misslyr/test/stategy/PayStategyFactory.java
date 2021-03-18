package com.misslyr.test.stategy;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author missli
 * @Description
 * @Date 2021/3/18 15:26
 **/
public class PayStategyFactory {
    private static PayStategyFactory pf = new PayStategyFactory();

    private static Map<Integer, String> stategyMap = new HashMap<Integer, String>();

    //扫描策略实现类
    static {
        Reflections reflections = new Reflections("com.misslyr.test.stategy");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(PayWay.class); //获取到这个包下面包含PayWay注解的类
        for (Class<?> clazz : classes) {
            //获取注解参数和全类名 放在map里
            PayWay payWay = clazz.getAnnotation(PayWay.class);
            stategyMap.put(payWay.value(), clazz.getCanonicalName());
        }
    }

    public static PayStategyFactory getInstance(){
        return pf;
    }

    //生产策略对象
    public PayStrategy creator(int payWay) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (PayStrategy)Class.forName(stategyMap.get(payWay)).newInstance(); //反射得到类实例
    }
}
