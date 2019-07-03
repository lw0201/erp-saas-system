package com.zhuoxun.it.base.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Salesman 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SalesmanVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 业务员ID
     */
    @Length(max = 64)
    @ApiModelProperty("业务员ID")
    private String id;

    /**
     * 租户id
     */
    @Length(max = 255)
    @ApiModelProperty("租户id")
    private String tenantId;

    /**
     * 姓名
     */
    @Length(max = 100)
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 手机号码
     */
    @Length(max = 15)
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 职位{1:普通业务员、0:主管}
     */
    @Length(max = 1)
    @ApiModelProperty("职位{1:普通业务员、0:主管}")
    private String position;

    /**
     * 代号
     */
    @Length(max = 100)
    @ApiModelProperty("代号")
    private String codeName;

    /**
     * 区域
     */
    @Length(max = 64)
    @ApiModelProperty("区域")
    private String area;

    /**
     * 状态
     */
    @Length(max = 1)
    @ApiModelProperty("状态")
    private String state;


}