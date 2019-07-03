package com.zhuoxun.it.base.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * TenantUser 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TenantUserVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    public TenantUserVO(String tenantId, String userId) {
        this.tenantId = tenantId;
        this.userId = userId;
    }

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 租户ID
     */
    @Length(max = 100)
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 用户ID
     */
    @Length(max = 100)
    @ApiModelProperty("用户ID")
    private String userId;

}