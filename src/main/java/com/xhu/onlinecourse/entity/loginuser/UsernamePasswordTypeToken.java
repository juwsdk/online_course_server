package com.xhu.onlinecourse.entity.loginuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
public class UsernamePasswordTypeToken extends UsernamePasswordToken {
    public UsernamePasswordTypeToken(String username, String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }
    private String loginType;
}
