package com.zhuoxun.it.base.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Product 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 产品
     */
    @Length(max = 64)
    @ApiModelProperty("产品")
    private String id;

    /**
     * 产品名称
     */
    @Length(max = 120)
    @ApiModelProperty("产品名称")
    private String name;

    /**
     * 编码
     */
    @Length(max = 100)
    @ApiModelProperty("编码")
    private String code;

    /**
     * 图片
     */
    @Length(max = 255)
    @ApiModelProperty("图片")
    private String icon;

    /**
     * 品类ID
     */
    @Length(max = 64)
    @ApiModelProperty("品类ID")
    private String categoryId;

    /**
     * 产品线ID
     */
    @Length(max = 64)
    @ApiModelProperty("产品线ID")
    private String productLineId;

    /**
     * 状态
     */
    @Length(max = 1)
    @ApiModelProperty("状态")
    private String state;


}