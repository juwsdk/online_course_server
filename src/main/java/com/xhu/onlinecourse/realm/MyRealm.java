package com.xhu.onlinecourse.realm;

import com.xhu.onlinecourse.entity.loginuser.UsernamePasswordTypeToken;
import com.xhu.onlinecourse.service.LoginRegisterService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
shrio的relam，进行授权和角色认证
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private LoginRegisterService loginRegisterService;

    @Override//自定义授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = (String) principalCollection.getPrimaryPrincipal();
        String roleType = (String) SecurityUtils.getSubject().getSession().getAttribute("loginType");
        //添加角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(roleType);
        info.addRole("all");
        return info;
    }

    @Override//自定义登录认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户身份信息
        UsernamePasswordTypeToken token = (UsernamePasswordTypeToken) authenticationToken;
        String userId = token.getUsername();
        String loginType = token.getLoginType();
        // 调用业务层获取用户信息（数据库中）
        Map<Long, String> userMap;
        try {
            userMap = loginRegisterService.getUserInfo(Long.valueOf(userId), loginType);
        } catch (Exception e) {
            throw new UnknownAccountException();//账户不存在
        }
        //验证密码
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), userMap.get(Long.valueOf(userId)), authenticationToken.getPrincipal().toString());
    }
}
