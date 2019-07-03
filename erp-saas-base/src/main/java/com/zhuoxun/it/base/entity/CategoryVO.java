package com.zhuoxun.it.base.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Category 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CategoryVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    public CategoryVO(String pid, String state) {
        this.pid = pid;
        this.state = state;
    }

    /**
     * 品类ID
     */
    @Length(max = 64)
    @ApiModelProperty("品类ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 父品类ID
     */
    @Length(max = 64)
    @ApiModelProperty("父品类ID")
    private String pid;

    /**
     * 品类名称
     */
    @Length(max = 100)
    @ApiModelProperty("品类名称")
    @NotBlank(groups = {Insert.class})
    private String name;

    /**
     * 租户ID
     */
    @Length(max = 64)
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 状态
     */
    @Length(max = 1)
    @ApiModelProperty("状态")
    private String state;

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

    @ApiModelProperty("二级品类")
    List<CategoryVO> children;

    @ApiModelProperty("字段名称")
    private List<CategoryFieldVO> categoryFields;

}