package com.zhuoxun.it.common.constans;

/**
 * 公共常量类
 * 
 * @author liwen
 *
 */
public class Constants {

    /**
     * 成功
     */
    public static final String SUCCESS = "Success";

    /**
     * 失败
     */
    public static final String FAIL = "fail";

    /**
     * MD5
     */
    public static final String MD5 = "MD5";

    /**
     * JWT 秘钥
     */
    public static final String JWT_TOKEN_KEY = "IQEVN58704JKHOILJ%*&(*@sad";

    /**
     * JWT token过期时间
     */
    public static final long JWT_EXPIR_TIME = 1000l * 60l * 60l * 30l;

    /**
     * redis token 过期时间
     */
    public static final long REDIS_TOKEN_EXPIR_TIME = 60l * 30l;

    /**
     * 登录token保存redis key前缀
     */
    public static final String LOGIN_PER = "login_";

    /**
     * erp域名
     */
    public static String COOKIE_DOMAIN = "";

    /**
     * 返给前段token 秘钥
     */
    public static String TOKEN_KEY = "fjsd489yGUY89^$@#*(KKO";

}
