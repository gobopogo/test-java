package com.misslyr.test.stategy;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @Author missli
 * @Description
 * @Date 2021/3/18 15:13
 **/
@Setter
//@AllArgsConstructor
public class Context {

    private PayStrategy payStrategy;

    private int payWay;

    Context (int payWay){
        this.payWay = payWay;
    }

    public void printPrice(double price){
        try {
            payStrategy = PayStategyFactory.getInstance().creator(payWay);
            System.out.println("您预计支付："+ payStrategy.pay(price)+"元");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
