package com.iszuo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil{

    // final 设置为常量
    private static final String KEY = "iszuo";

    // 生成token
    public static String getToken(Map<String, Object> claims){
        return JWT.create() // 创建JWT
                .withClaim("claims",claims) // 添加数据载荷（用户信息）
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))  // 过期时间
                .sign(Algorithm.HMAC256(KEY));  // 加密算法，密钥
    }

    // 解析token
    public static Map<String, Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(KEY))  // 解析算法，密钥
                .build()    // 生成验证器
                .verify(token)  // 验证token，解析token对象
                .getClaim("claims") // 获取载荷（用户信息)
                .asMap();   // 转map后返回
    }

}
