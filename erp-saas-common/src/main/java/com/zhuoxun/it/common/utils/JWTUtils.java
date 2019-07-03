package com.zhuoxun.it.common.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhuoxun.it.common.constans.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

    private static Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    // Sample method to construct a JWT

    /**
     * 构建jwt
     * 
     * @param id
     *            唯一标识
     * @param issuer
     *            创建者
     * @param subject
     *            自定义参数 json字符串
     * @param ttlMillis
     *            过期时间
     * @return
     */
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // We will sign our JWT with our ApiKey secret
        String signingKey = java.util.Base64.getEncoder().encodeToString(Constants.JWT_TOKEN_KEY.getBytes());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
            .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * 解析jwt
     * 
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(java.util.Base64.getEncoder().encodeToString(Constants.JWT_TOKEN_KEY.getBytes()))
                .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            logger.error("JWTUtils.parseJWT========无效的token", e);
            return null;
        }
    }

}