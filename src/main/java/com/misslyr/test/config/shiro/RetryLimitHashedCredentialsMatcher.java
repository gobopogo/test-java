//package com.misslyr.test.config.shiro;
//
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.ExcessiveAttemptsException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
//
//    private Cache<String, AtomicInteger> passwordRetryCache;
//    private Cache<String, AtomicInteger> captchaCache;
//
//    public RetryLimitHashedCredentialsMatcher(EhCacheManager cacheManager) {
//        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
//        captchaCache = cacheManager.getCache("captchaCache");
//    }
//
//    @Override
//    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        String username = usernamePasswordToken.getUsername();
//        AtomicInteger retryCount = passwordRetryCache.get(username);
//        if (retryCount == null) {
//            retryCount = new AtomicInteger(0);
//            passwordRetryCache.put(username, retryCount);
//        }
//        if (retryCount.incrementAndGet() > 5) {
//            throw new ExcessiveAttemptsException();
//        }
//        boolean matches = super.doCredentialsMatch(token, info);
//        if (matches) {
//            passwordRetryCache.remove(username);
//        }
//        return matches;
//    }
//}
