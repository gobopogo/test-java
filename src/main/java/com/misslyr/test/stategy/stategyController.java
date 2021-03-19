package com.misslyr.test.stategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author missli
 * @Description 两种方法实现的策略模式
 * @Date 2021/3/18 15:20
 **/
@RestController
@RequestMapping("/stategy")
public class stategyController {

    @Autowired
    Map<String, PayStrategy> stategys;

    /** 
     * @Author missli
     * @Description payWay 支付方式
     * @Date 2021/3/18 16:42
     * http://127.0.0.1:8111/testJava/stategy/pay?payWay=1
     */
    @GetMapping("/pay")
    public void payTest(@RequestParam int payWay){

        Context ctx = new Context(payWay);
        ctx.printPrice(10);

        BigDecimal value = stategys.get("AliPay").pay(10);
        System.out.println("Spring管理的策略模式 您预计支付：" + value + "元");

    }
}
