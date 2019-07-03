package com.zhuoxun.it.base.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ProductLine 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductLineVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 产品线ID
     */
    @ApiModelProperty("产品线ID")
    private Integer id;

    /**
     * 编码
     */
    @Length(max = 100)
    @ApiModelProperty("编码")
    private String code;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 描述
     */
    @Length(max = 200)
    @ApiModelProperty("描述")
    private String describe;

    /**
     * 状态
     */
    @Length(max = 1)
    @ApiModelProperty("状态")
    private String state;


}