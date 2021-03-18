package com.misslyr.test.stategy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author missli
 * @Description
 * @Date 2021/3/18 15:20
 **/
@RestController
@RequestMapping("/stategy")
public class stategyController {

    /** 
     * @Author missli
     * @Description payWay 支付方式
     * @Date 2021/3/18 16:42
     */
    @GetMapping("/pay")
    public void payTest(@RequestParam int payWay){

        Context ctx = new Context(payWay);
        ctx.printPrice(10);
    }
}
