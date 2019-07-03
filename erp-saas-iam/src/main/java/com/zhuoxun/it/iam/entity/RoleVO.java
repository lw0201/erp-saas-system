package com.zhuoxun.it.iam.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Role 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 角色编码
     */
    @Length(max = 100)
    @ApiModelProperty("角色编码")
    private String roleCode;

    /**
     * 角色名称
     */
    @Length(max = 100)
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 租户ID
     */
    @Length(max = 64)
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 描述
     */
    @Length(max = 255)
    @ApiModelProperty("描述")
    private String describe;

}