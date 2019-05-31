package com.misslyr.test.config.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Author missli
 * @Description
 * @Date 2019/5/31 17:50
 **/
public class ManagerAuthorizingRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ManagerAuthorizingRealm.class);

/*    @Autowired
    SysmgrUserRepository sysmgrUserRepository;*/

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        /*String loginName = (String) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> codes = sysmgrUserRepository.findPermissionCodeByLoginName(loginName);
        if (codes != null && !codes.isEmpty()) {
            info.addStringPermissions(codes);
        }
        return info;*/
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("##################执行Shiro登录认证##################");
        /*UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        SysmgrUserEntity userEntity = sysmgrUserRepository.findByLoginName(username);
        if (userEntity==null) {
            throw  new UnknownAccountException("没有此用户:" + username);
        }
        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(userEntity.getLoginName(), userEntity.getLoginPwd(), userEntity.getUsername());
        if(authenticationToken instanceof SSOUsernamePasswordToken){
            SSOUsernamePasswordToken ssoUsernamePasswordToken = (SSOUsernamePasswordToken)authenticationToken;
            ai = new SimpleAuthenticationInfo(userEntity.getLoginName(), "3EF7164D1F6167CB9F2658C07D3C2F0A", userEntity.getUsername());
        }
        ai.setCredentialsSalt(ByteSource.Util.bytes(username));

        return ai;*/
    }
