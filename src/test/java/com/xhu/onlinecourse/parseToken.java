package com.xhu.onlinecourse;

import com.xhu.onlinecourse.utils.JwtUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class parseToken {
    @Test
    public void testToken() {
        Map<String, Object> map = new HashMap();
        map.put("userId", "31201900");
        map.put("loginType", "student");
        String token = JwtUtils.generateToken(map);
        System.out.println(token);
        System.out.println(JwtUtils.parseToken(token));
        System.out.println(JwtUtils.validateToken(token));
    }
}
