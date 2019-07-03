package com.zhuoxun.it.base.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Lookup 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LookupVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 字典编码
     */
    @Length(max = 100)
    @ApiModelProperty("字典编码")
    @NotBlank(groups = {Insert.class, Update.class})
    private String lookupCode;

    /**
     * 字典名称
     */
    @Length(max = 100)
    @ApiModelProperty("字典名称")
    @NotBlank(groups = {Insert.class, Update.class})
    private String lookupName;

    /**
     * 图片
     */
    @Length(max = 255)
    @ApiModelProperty("描述")
    private String describe;

    /**
     * 状态(Y:启用，N禁用)
     */
    @Length(max = 1)
    @ApiModelProperty("状态(Y:启用，N禁用)")
    @NotBlank(groups = {Insert.class, Update.class})
    private String state;

    /**
     * 租户ID
     */
    @Length(max = 100)
    @ApiModelProperty("租户ID")
    private String tenantId;

    @Valid
    private List<LookupConfigVO> lookupConfigs;

}