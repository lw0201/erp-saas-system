package com.zhuoxun.it.iam.aspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.handler.BusinessCode;
import com.zhuoxun.it.common.utils.JWTUtils;
import com.zhuoxun.it.common.utils.RedisUtils;
import com.zhuoxun.it.common.utils.StringUtils;
import com.zhuoxun.it.common.utils.TokenUtil;

import io.jsonwebtoken.Claims;

@Aspect
@Component
public class ControllerAspect {

    private static final Logger log = LoggerFactory.getLogger(ControllerAspect.class);

    public static long startTime;

    public static long endTime;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisUtils redisUtils;

    private static List<String> methods = new ArrayList<String>();

    private static List<String> white = new ArrayList<String>();

    static {
        methods.add("save*");
        methods.add("update*");
        methods.add("insert*");
        methods.add("modify*");
        white.add("*login*");
    }

    /** @PointCut注解表示表示横切点，哪些方法需要被横切 */
    @Pointcut("execution(public * com.zhuoxun.it.iam.controller.*.*(..))")
    public void point() {}

    /** @Before注解表示在具体的方法之前执行 */
    @Before("point()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
 
        log.info("前置切面before……");
        startTime = System.currentTimeMillis();
        String requestURI = request.getRequestURI();
        String remoteAddr = StringUtils.getIpAddress(request);
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求url=" + requestURI + ",客户端ip=" + remoteAddr + ",请求方式=" + requestMethod + ",请求的类名="
            + declaringTypeName + ",方法名=" + methodName + ",入参=" + Arrays.toString(args));
        
        if (args != null && !methodName.equals("login")) {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            String jwt = (String)redisUtils.get(tokenUtil.decryptRedisToken(token));
            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(jwt)) {
                return;
            }
            Claims claims = JWTUtils.parseJWT(jwt);
            JSONObject json = (JSONObject)JSONObject.parse(claims.getSubject());
            if (checkMethod(methodName)) {
                return;
            }
            for (int i = 0; i < args.length; i++) {
                Map<String, Object> map = com.zhuoxun.it.common.utils.BeanUtils.beanToMap(args[i]);
                if (map.containsKey("createdBy")) {
                    map.put("createdBy", json.getString("userId"));
                }
                if (map.containsKey("lastUpdateBy")) {
                    map.put("lastUpdateBy", json.getString("userId"));

                }
            }
        }
    }

    @Around("point()")
    public Object trackInfo(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        String methodName = pjp.getSignature().getName();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwt = (String)redisUtils.get(tokenUtil.decryptRedisToken(token));
        if (!checkwhite(methodName) && (StringUtils.isEmpty(token) || StringUtils.isEmpty(jwt))) {
            response.setStatus(401);
            return new Result<>(BusinessCode.NOT_AUTH, "请重新登录");
        }
        return pjp.proceed();
    }

    private boolean checkMethod(String uri) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String mtd : methods) {
            if (pathMatcher.match(mtd, uri)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkwhite(String uri) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String url : white) {
            if (pathMatcher.match(url, uri)) {
                return true;
            }
        }
        return false;
    }

    /** @After注解表示在方法执行之后执行 */
    @After("point()")
    public void after() {
        endTime = System.currentTimeMillis() - startTime;
        log.info("后置切面after……");
    }

    /*@AfterReturning注解用于获取方法的返回值*/
    @AfterReturning(pointcut = "point()", returning = "object")
    public void afterReturning(Object object) {
        log.info("本次接口耗时={}ms", endTime);
        log.info("afterReturning={}", object);
    }

}
