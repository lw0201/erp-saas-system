
package com.zhuoxun.it.iam.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.utils.TokenUtil;
import com.zhuoxun.it.iam.entity.UserVO;
import com.zhuoxun.it.iam.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "登录管理")
public class LoginController {

    @Autowired
    IUserService iUserService;

    @Autowired
    private TokenUtil tokenUtil;

    @ApiOperation(value = "管理端登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody UserVO userVO, HttpServletResponse response) {
        return new Result<Map<String, Object>>()
            .success(iUserService.login(userVO, response, UserType.System.getCode()));
    }

    @ApiOperation(value = "登出")
    @PostMapping("/logOut")
    public Result<String> logOut() {
        ServletRequestAttributes servletRequestAttributes =
            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        tokenUtil.logout(request.getHeader(HttpHeaders.AUTHORIZATION), servletRequestAttributes.getResponse());
        return new Result<String>().success(null);
    }

    @ApiOperation(value = "租户登录")
    @PostMapping("/tenantlogin")
    public Result<Map<String, Object>> tenantlogin(@RequestBody UserVO userVO, HttpServletResponse response) {
        Map<String, Object> map = iUserService.login(userVO, response, UserType.Tenant.getCode());
        return new Result<Map<String, Object>>().success(map);
    }

}
