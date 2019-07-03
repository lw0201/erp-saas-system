package com.zhuoxun.it.gateway.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import com.netflix.zuul.context.RequestContext;

public class RequestContextUtils {

    public static RequestContext getCurrentContext() {
        return RequestContext.getCurrentContext();
    }

    public static HttpServletRequest getRequest() {
        return getCurrentContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getCurrentContext().getResponse();
    }

    public static String getToken() {
        return getRequest().getHeader(HttpHeaders.AUTHORIZATION);
    }

}
