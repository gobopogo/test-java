package com.misslyr.test.stategy;

import java.math.BigDecimal;

/**
 * @Author missli
 * @Description 支付策略
 * @Date 2021/3/18 15:03
 **/
public interface PayStrategy {

    BigDecimal pay(double price);
}
