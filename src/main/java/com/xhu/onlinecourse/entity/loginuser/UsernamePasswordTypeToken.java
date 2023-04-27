package com.xhu.onlinecourse.entity.loginuser;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;
/*
继承shrio的usernamePasswordToken，多传入一个loginType，来判别权限
 */
@Data
public class UsernamePasswordTypeToken extends UsernamePasswordToken {

    public UsernamePasswordTypeToken(String username, String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }
    private String loginType;
}
