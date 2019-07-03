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
 * CategoryField 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryFieldVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 品类字段管理ID
     */
    @Length(max = 64)
    @ApiModelProperty("品类字段管理ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 名称
     */
    @Length(max = 100)
    @ApiModelProperty("名称")
    @NotBlank(groups = {Insert.class})
    private String name;

    /**
     * 品类ID
     */
    @Length(max = 64)
    @ApiModelProperty("品类ID")
    @NotBlank(groups = {Insert.class})
    private String categoryId;

    /**
     * 显示名称
     */
    @Length(max = 100)
    @ApiModelProperty("显示名称")
    @NotBlank(groups = {Insert.class})
    private String displyName;

    /**
     * 是否必填
     */
    @Length(max = 1)
    @ApiModelProperty("是否必填")
    private String required;

    /**
     * 来源{1：系统，2：租户}
     */
    @Length(max = 1)
    @ApiModelProperty("来源{1：系统，2：租户}")
    @NotBlank(groups = {Insert.class})
    private String source;

    /**
     * 排列顺序
     */
    @ApiModelProperty("排列顺序")
    private Integer sort;

    /**
     * 所属属性ID
     */
    @Length(max = 64)
    @ApiModelProperty("所属属性ID")
    @NotBlank(groups = {Insert.class})
    private String attributeId;

}