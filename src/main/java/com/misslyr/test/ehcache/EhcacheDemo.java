package com.misslyr.test.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @Author missli
 * @Description
 * @Date 2019/5/31 15:36
 **/
@Service
public class EhcacheDemo {

    @Autowired
    CacheManager cacheManager;

    public void cacheTest(){
        Cache cache = cacheManager.getCache("testEhcache");
        cache.put("cache1","HelloWorld");
        System.out.println("====================ehcache："+cache.get("cache1").get());
    }
}
