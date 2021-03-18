package com.misslyr.test.stategy.impl;

import com.misslyr.test.stategy.PayStrategy;
import com.misslyr.test.stategy.PayWay;

import java.math.BigDecimal;

/**
 * @Author missli
 * @Description 支付宝8折
 * @Date 2021/3/18 15:07
 **/
@PayWay(1)
public class AliPay implements PayStrategy {
    @Override
    public BigDecimal pay(double price) {
        return BigDecimal.valueOf(price * 0.8);
    }
}
