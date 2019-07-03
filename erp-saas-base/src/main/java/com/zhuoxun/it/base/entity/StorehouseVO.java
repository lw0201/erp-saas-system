package com.zhuoxun.it.base.entity;


import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Storehouse 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StorehouseVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @Length(max = 64)
    @ApiModelProperty("仓库ID")
    private String id;

    /**
     * 父级id
     */
    @Length(max = 64)
    @ApiModelProperty("父级id")
    private String pid;

    /**
     * 租户id
     */
    @Length(max = 100)
    @ApiModelProperty("租户id")
    private String tenantId;

    /**
     * 仓库名称
     */
    @Length(max = 100)
    @ApiModelProperty("仓库名称")
    private String name;

    /**
     * 仓库编码
     */
    @Length(max = 100)
    @ApiModelProperty("仓库编码")
    private String code;

    /**
     * 仓库类型{1:批发,2:零售}
     */
    @Length(max = 1)
    @ApiModelProperty("仓库类型{1:批发,2:零售}")
    private String type;

    /**
     * 状态
     */
    @Length(max = 1)
    @ApiModelProperty("状态")
    private String state;

    /**
     * 说明备注
     */
    @Length(max = 255)
    @ApiModelProperty("说明备注")
    private String remark;


}