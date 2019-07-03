
package com.zhuoxun.it.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuoxun.it.common.constans.Constants;
import com.zhuoxun.it.common.exception.ApplicationException;

import io.jsonwebtoken.Claims;

@Component
public class TokenUtil {

    @Autowired
    private RedisUtils redisUtils;

    public void deleteToken(String key) {
        JSONArray tokens = (JSONArray)redisUtils.get(key);
        if (tokens != null && !tokens.isEmpty()) {
            // 清理旧的token key 信息
            for (int i = 0; i < tokens.size(); i++) {
                String tokenKey = tokens.get(i).toString();
                redisUtils.del(tokenKey);
            }
        }
    }

    public boolean checkToken(String newToken) {
        String jwt = (String)redisUtils.get(decryptRedisToken(newToken));
        if (StringUtils.isEmpty(jwt)) {
            return false;
        }
        Claims claims = JWTUtils.parseJWT(jwt);
        if (claims == null) {
            return false;
        }
        return true;
    }

    /**
     * 设置token
     * 
     * @param token
     *            加密token
     * @param response
     */
    public void setToken(String token, HttpServletResponse response) {
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
    }

    /**
     * 刷新token缓存时间
     * 
     * @param token
     *            加密token
     * @param response
     */
    public void refreshCache(String token, HttpServletResponse response) {
        String jwt = (String)redisUtils.get(decryptRedisToken(token));
        if (StringUtils.isEmpty(jwt)) {
            throw new ApplicationException("请重新登陆");
        }
        Claims claims = JWTUtils.parseJWT(jwt);
        if (claims == null) {
            throw new ApplicationException("请重新登陆");
        }
        JSONObject json = JSONObject.parseObject(claims.getSubject());
        redisUtils.set(decryptRedisToken(token), jwt, Constants.REDIS_TOKEN_EXPIR_TIME);
        redisUtils.expire(Constants.LOGIN_PER + json.getString("userId"), Constants.REDIS_TOKEN_EXPIR_TIME);
    }

    /**
     * 解密token
     * 
     * @param token
     * @return redis key
     */
    public String decryptRedisToken(String token) {
        return EncryptUtils.decryptByAES(token, Constants.TOKEN_KEY);
    }

    /**
     * 登出token(单点)
     * 
     * @param token
     *            加密token
     * @param response
     */
    public void logout(String token, HttpServletResponse response) {
        String key = decryptRedisToken(token);
        if (!StringUtils.isEmpty(key)) {
            delToken(key); // 清除缓存中的token
        }
    }

    public void delToken(String key) {
        String[] param = key.split("_");
        redisUtils.del(key);
        redisUtils.del(Constants.LOGIN_PER + param[1]);
    }

    public boolean checkTokenOut(String token) {
        String key = decryptRedisToken(token);
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        String jwt = (String)redisUtils.get(key);
        if (StringUtils.isEmpty(jwt)) {
            String[] param = key.split("_");
            JSONArray tokens = (JSONArray)redisUtils.get(Constants.LOGIN_PER + param[1]);
            if (tokens != null && !tokens.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public JSONObject getUserInfo(HttpServletRequest request) {
        String key = decryptRedisToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (StringUtils.isEmpty(key)) {
            throw new ApplicationException("请重新登陆");
        }
        String jwt = (String)redisUtils.get(key);
        if (StringUtils.isEmpty(jwt)) {
            throw new ApplicationException("请重新登陆");
        }
        Claims claims = JWTUtils.parseJWT(jwt);
        if (claims == null) {
            throw new ApplicationException("请重新登陆");
        }
        JSONObject json = JSONObject.parseObject(claims.getSubject());
        return json;
    }

}
