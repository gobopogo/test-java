package com.misslyr.test.config.ehcache;

import org.springframework.context.annotation.Configuration;

/**
 * @Author missli
 * @Description 若启动类已经注释了@EnableCaching 这个类就不用了
 * @Date 2019/5/31 15:44
 **/
@Configuration
public class EhcacheConfig {

    /*@Bean
    public EhCacheCacheManager ehCacheCacheManager(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        return ehCacheCacheManager;
    }*/

}
