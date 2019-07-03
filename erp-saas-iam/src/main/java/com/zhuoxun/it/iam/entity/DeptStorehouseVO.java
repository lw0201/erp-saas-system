package com.zhuoxun.it.iam.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DeptStorehouse 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptStorehouseVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 部门ID
     */
    @Length(max = 100)
    @ApiModelProperty("部门ID")
    private String deptId;

    /**
     * 仓库ID
     */
    @Length(max = 100)
    @ApiModelProperty("仓库ID")
    private String storehouseId;


}