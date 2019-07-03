package com.zhuoxun.it.gateway.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zhuoxun.it.common.utils.TokenUtil;
import com.zhuoxun.it.gateway.utils.RequestContextUtils;

/**
 * 登录拦截
 * 
 * @author liwen
 */
@Component
public class LoginFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    public static final int LOGIN_FILTER_ORDER = 101;

    @Autowired
    private TokenUtil tokenUtil;

    private static List<String> urls = new ArrayList<String>();

    static {
        urls.add("/iam/login");
        urls.add("/iam/tenantlogin");
        urls.add("/**/v2/api-docs");
    }

    @Override
    public boolean shouldFilter() {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String url = RequestContextUtils.getRequest().getRequestURI();
        for (String urlStr : urls) {
            if (pathMatcher.match(urlStr, url)) {
                logger.debug("URL White List: " + url);
                return false;
            }
        }
        return true;
    }

    /**
     * 返回过滤类型，有：pre，route，post，error
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 返回执行优先级，数字越小，优先级越高，越早执行
     */
    @Override
    public int filterOrder() {
        return LOGIN_FILTER_ORDER;
    }

    @Override
    public Object run() throws ZuulException {
        String token = RequestContextUtils.getToken();
        if (tokenUtil.checkToken(token)) {
            tokenUtil.refreshCache(token, RequestContextUtils.getResponse());
            return null;
        } else {
            if (tokenUtil.checkTokenOut(token)) {
                tokenUtil.logout(token, RequestContextUtils.getResponse());
                RequestContextUtils.getResponse().setStatus(HttpStatus.GONE.value());
                RequestContext.getCurrentContext().setSendZuulResponse(false);
                return null;
            }
        }
        RequestContext.getCurrentContext().setSendZuulResponse(false);
        RequestContextUtils.getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
        return null;
    }

}
