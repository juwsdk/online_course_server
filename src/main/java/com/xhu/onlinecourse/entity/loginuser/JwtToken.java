package com.xhu.onlinecourse.entity.loginuser;

import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String jwt;

    //都返回jwt串
    @Override
    public Object getPrincipal() {
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }
}
