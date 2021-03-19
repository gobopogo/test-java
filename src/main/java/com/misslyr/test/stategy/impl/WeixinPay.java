package com.misslyr.test.stategy.impl;

import com.misslyr.test.stategy.PayStrategy;
import com.misslyr.test.stategy.PayWay;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author missli
 * @Description 微信支付9折
 * @Date 2021/3/18 15:05
 **/
@PayWay(2)
@Component("WeixinPay")
public class WeixinPay implements PayStrategy {

    @Override
    public BigDecimal pay(double price) {
        return BigDecimal.valueOf(price * 0.9);
    }
}
