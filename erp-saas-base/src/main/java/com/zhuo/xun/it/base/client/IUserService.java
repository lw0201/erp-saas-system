package com.zhuo.xun.it.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zhuoxun.it.base.config.FeignConfiguration;
import com.zhuoxun.it.base.entity.TenantUserVO;
import com.zhuoxun.it.common.Result;

@FeignClient(value = "iam", configuration = FeignConfiguration.class)
public interface IUserService {

    @PostMapping("/v1/tenantuser/query")
    public Result<TenantUserVO> query(@RequestBody TenantUserVO entity);
}
