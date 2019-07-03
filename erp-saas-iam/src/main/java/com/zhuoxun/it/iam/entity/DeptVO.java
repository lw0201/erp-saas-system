package com.zhuoxun.it.iam.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dept 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 父级id
     */
    @Length(max = 64)
    @ApiModelProperty("父级id")
    private String pid;

    /**
     * 部门等级 0:根目录
     */
    @ApiModelProperty("部门等级 0:根目录")
    private Integer deptLevel;

    /**
     * 部门编码
     */
    @Length(max = 100)
    @ApiModelProperty("部门编码")
    private String deptCode;

    /**
     * 部门名称
     */
    @Length(max = 100)
    @ApiModelProperty("部门名称")
    private String deptName;

    /**
     * 租户ID
     */
    @Length(max = 64)
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 部门地址
     */
    @Length(max = 255)
    @ApiModelProperty("部门地址")
    private String address;

    /**
     * 描述
     */
    @Length(max = 200)
    @ApiModelProperty("描述")
    private String describe;


}