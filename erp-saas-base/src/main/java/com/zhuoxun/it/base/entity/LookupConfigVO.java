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
 * LookupConfig 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LookupConfigVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 字典名称
     */
    @Length(max = 100)
    @ApiModelProperty("字典名称")
    @NotBlank(groups = {Insert.class})
    private String key;

    /**
     * 字典编码
     */
    @Length(max = 100)
    @ApiModelProperty("字典编码")
    @NotBlank(groups = {Insert.class})
    private String value;

    /**
     * 数据字典ID
     */
    @Length(max = 64)
    @ApiModelProperty("数据字典ID")
    private String looupId;

}