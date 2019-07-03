package com.zhuoxun.it.base.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Attribute 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AttributeVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 属性名称
     */
    @Length(max = 64)
    @ApiModelProperty("属性ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 属性名称
     */
    @Length(max = 100)
    @ApiModelProperty("属性名称")
    @NotBlank(groups = {Insert.class})
    private String name;

    /**
     * 租户ID
     */
    @Length(max = 64)
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 属性扩展字段
     */
    @ApiModelProperty("属性扩展字段")
    private String attr;

    /**
     * 状态{Y：启用，N：禁用}
     */
    @Length(max = 1)
    @ApiModelProperty("状态{Y：启用，N：禁用}")
    private String state;

    /**
     * 类型
     */
    @Length(max = 30)
    @ApiModelProperty("类型")
    @NotBlank(groups = {Insert.class})
    private String type;

    /**
     * 描述
     */
    @Length(max = 200)
    @ApiModelProperty("描述")
    private String describe;

}