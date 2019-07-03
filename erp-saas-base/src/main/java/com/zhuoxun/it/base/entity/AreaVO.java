package com.zhuoxun.it.base.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Area 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AreaVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @Length(max = 64)
    @ApiModelProperty("区域ID")
    private String id;

    /**
     * 租户id
     */
    @Length(max = 100)
    @ApiModelProperty("租户id")
    private String tenantId;

    /**
     * 区域名称
     */
    @Length(max = 100)
    @ApiModelProperty("区域名称")
    private String name;

    /**
     * 状态(Y:启用,N:禁用)
     */
    @Length(max = 1)
    @ApiModelProperty("状态(Y:启用,N:禁用)")
    private String state;

    /**
     * 描述
     */
    @Length(max = 200)
    @ApiModelProperty("描述")
    private String describe;


}