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
 * TenantType 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantTypeVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 企业类型名称
     */
    @Length(max = 100)
    @ApiModelProperty("企业类型名称")
    @NotBlank(groups = {Insert.class, Update.class})
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty("状态{Y:启用,N:停用}")
    private String state;

    /**
     * 描述
     */
    @Length(max = 200)
    @ApiModelProperty("描述")
    private String describe;

}