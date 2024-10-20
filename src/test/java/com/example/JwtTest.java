package com.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtTest {

    //jwt令牌生成
    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        //生成jwt代码
        String tocken = JWT.create()
            .withClaim("user", claims)//添加载荷
            .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加过期时间
            .sign(Algorithm.HMAC256("wxtdsg"));//指定算法，配置密钥
        System.out.println(tocken);
    }

    @Test
    public void testParse(){
        //定义字符串，模拟用户传过来的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                        ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3Mjk0NzI4ODl9" +
                        ".4ilQEFHMcbszGOj7sSyVSvZpmBYsu-m5zXq3WwJU4S0";
        String token1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3Mjk0Mjc3MTN9.m0Ksj60M1-CAYJ5PT3S5qSbXptc3cb4VzbsxrIz3s8o";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("wxtdsg")).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);//验证token，生成一个解析后的jwt对象
        Map<String,Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        //如果篡改了头部和载荷部分的数据，那么验证失败
        //如果密钥改了，验证失败
        //token过期了也会验证失败

    }
}
