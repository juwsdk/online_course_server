package com.xhu.onlinecourse.realm;

import com.xhu.onlinecourse.entity.loginuser.JwtToken;
import com.xhu.onlinecourse.entity.loginuser.User;
import com.xhu.onlinecourse.service.LoginRegisterService;
import com.xhu.onlinecourse.utils.JwtUtils;
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
        User user = (User) principalCollection.getPrimaryPrincipal();
        String userId = String.valueOf(user.getUserId());
        String roleType = user.getLoginType();
        //添加角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(roleType);
        info.addRole("all");
        return info;
    }

    @Override//自定义登录认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //取出token
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();
        // 获取用户身份信息
        Map<String, Object> claims = JwtUtils.parseToken(token);
        String userId = String.valueOf(claims.get("userId"));
        String loginType = String.valueOf(claims.get("loginType"));
        //封装信息，作为principal
        User user = new User(Long.parseLong(userId), null, loginType);
        System.out.println(userId);
        if (!loginRegisterService.checkUserId(user))
            throw new UnknownAccountException();//账户不存在
        return new SimpleAuthenticationInfo(user, jwtToken.getCredentials(), this.getName());
    }

    @Override//设置生成的为JwtToken
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
