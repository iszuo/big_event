package com.iszuo;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest{
    @Test
    public void testGen(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        String token = JWT.create()
                .withClaim("user",claims)    // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))  // 获取当前系统的毫秒，过期时间
                .sign(Algorithm.HMAC256("iszuo"));  // 指定算法，设置密钥

        System.out.println(token);
    }





    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3Mjg0ODMzNTN9" +
                ".XhdvZrhbWbdB0rP7MjO9cVzoTMqXpcoqzBjbuZ178-4";
        // 生成验证器     申请JWT验证器（解密算法（密钥））.build
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("iszuo")).build();
        // 验证token，生成解析后的token对象
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        // 获取载荷
        Map<String,Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
