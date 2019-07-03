package com.zhuoxun.it.base.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Supplier 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @Length(max = 64)
    @ApiModelProperty("客户ID")
    private String id;

    /**
     * 企业名称
     */
    @Length(max = 100)
    @ApiModelProperty("企业名称")
    private String name;

    /**
     * 统一信用代码
     */
    @Length(max = 50)
    @ApiModelProperty("统一信用代码")
    private String creditCode;

    /**
     * 代号
     */
    @Length(max = 100)
    @ApiModelProperty("代号")
    private String codeName;

    /**
     * 简称
     */
    @Length(max = 100)
    @ApiModelProperty("简称")
    private String call;

    /**
     * 编码
     */
    @Length(max = 100)
    @ApiModelProperty("编码")
    private String code;

    /**
     * 区域
     */
    @Length(max = 64)
    @ApiModelProperty("区域")
    private String areaId;

    /**
     * 状态
     */
    @Length(max = 1)
    @ApiModelProperty("状态")
    private String state;


}