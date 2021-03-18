//package com.misslyr.test.config.shiro;
//
//import net.sf.ehcache.CacheManager;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.session.mgt.DefaultSessionManager;
//import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
//import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @Author missli
// * @Description
// * @Date 2019/5/31 17:41
// **/
//@Configuration
//public class ShiroConfig {
//
//    @Bean(name = "shiroEhCacheManager")
//    public EhCacheManager ehCacheManager(CacheManager ehCacheCacheManager) {
//        EhCacheManager em = new EhCacheManager();
//        em.setCacheManager(ehCacheCacheManager);
//        return em;
//    }
//
//    @Bean(name = "javaUuidSessionIdGenerator")
//    public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator() {
//        return new JavaUuidSessionIdGenerator();
//    }
//
//
//    @Bean(name = "sessionDAO")
//    public EnterpriseCacheSessionDAO sessionDAO(JavaUuidSessionIdGenerator javaUuidSessionIdGenerator) {
//        final EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
//        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
//        sessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator);
//        return sessionDAO;
//    }
//
//    @Bean(name = "sessionManager")
//    public DefaultSessionManager sessionManager(EnterpriseCacheSessionDAO sessionDAO, SimpleCookie sessionIdCookie) {
//        final DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(sessionDAO);
//        sessionManager.setGlobalSessionTimeout(1800000);
//        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionIdCookie(sessionIdCookie);
//        sessionManager.setSessionIdCookieEnabled(true);
//        return sessionManager;
//    }
//
//    @Bean(name = "rememberMeCookie")
//    public SimpleCookie rememberMeCookie() {
//        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
//        rememberMeCookie.setHttpOnly(true);
//        rememberMeCookie.setMaxAge(2592000);
//        return rememberMeCookie;
//    }
//
//    @Bean(name = "rememberMeManager")
//    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
//        cookieRememberMeManager.setCookie(rememberMeCookie);
//        return cookieRememberMeManager;
//    }
//
//    @Bean(name = "retryLimitHashedCredentialsMatcher")
//    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(EhCacheManager ehCacheManager) {
//        RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager);
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(2);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }
//
//    @Bean(name = "managerAuthorizingRealm")
//    public ManagerAuthorizingRealm managerAuthorizingRealm(EhCacheManager cacheManager,RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher) {
//        ManagerAuthorizingRealm realm = new ManagerAuthorizingRealm();
//        realm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher);
//        realm.setCacheManager(cacheManager);
//        return realm;
//    }
//
//    /**
//     * 注入 securityManager
//     */
//    @Bean(name = "securityManager")
//    @Primary
//    public DefaultWebSecurityManager defaultWebSecurityManager(ManagerAuthorizingRealm managerAuthorizingRealm, EhCacheManager ehCacheManager, DefaultSessionManager sessionManager, CookieRememberMeManager rememberMeManager) {
//        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
//        dwsm.setRealm(managerAuthorizingRealm);
//        dwsm.setCacheManager(ehCacheManager);
//        dwsm.setSessionManager(sessionManager);
//        dwsm.setRememberMeManager(rememberMeManager);
//        return dwsm;
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
//        shiroFilterFactoryBean.setLoginUrl("/notLogin");
//        // 设置无权限时跳转的 url;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
//
//        // 设置拦截器
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        //游客，开发权限
//        filterChainDefinitionMap.put("/guest/**", "anon");
//        //用户，需要角色权限 “user”
//        filterChainDefinitionMap.put("/user/**", "roles[user]");
//        //管理员，需要角色权限 “admin”
//        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
//        //开放登陆接口
//        filterChainDefinitionMap.put("/login", "anon");
//        //测试接口
//        filterChainDefinitionMap.put("/stategy/**", "anon");
//        //其余接口一律拦截
//        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
//        filterChainDefinitionMap.put("/**", "authc");
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        System.out.println("Shiro拦截器工厂类注入成功");
//
//        return shiroFilterFactoryBean;
//    }
//}
