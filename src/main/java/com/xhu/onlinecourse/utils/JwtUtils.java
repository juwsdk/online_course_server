package com.xhu.onlinecourse.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET_KEY = "WFDNJUHU&^&762JLP";

    // 生成Token
    public static String generateToken(Map<String, Object> claims) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", userId);
//        claims.put("userType", userType);

        Calendar expireTime = Calendar.getInstance();
        expireTime.add(Calendar.DATE, 7);//7天过期

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")//header设置为jwt
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expireTime.getTime())
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 解析Token，返回包含多个信息的Map
    public static Map<String, Object> parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    // 验证Token是否有效
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}