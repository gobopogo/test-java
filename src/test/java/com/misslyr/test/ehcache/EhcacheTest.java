package com.misslyr.test.ehcache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author missli
 * @Description
 * @Date 2019/5/31 15:41
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class EhcacheTest {

    @Autowired
    EhcacheDemo ehcacheDemo;

    @Test
    public void test1(){
        ehcacheDemo.cacheTest();
    }

}
