package com.zhuoxun.it.iam.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TenantRole 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantRoleVO extends BaseVO {

    private static final long serialVersionUID = 1L;

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
     * 角色ID
     */
    @Length(max = 100)
    @ApiModelProperty("角色ID")
    private String roleId;

}