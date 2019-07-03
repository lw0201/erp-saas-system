package com.zhuoxun.it.iam.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Menu 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 
     */
    @Length(max = 64)
    @ApiModelProperty("")
    private String pid;

    /**
     * 菜单名称
     */
    @Length(max = 100)
    @ApiModelProperty("菜单名称")
    private String menuName;

    /**
     * 菜单类型{0:菜单,1:子菜单,2:按钮}
     */
    @Length(max = 100)
    @ApiModelProperty("菜单类型{0:菜单,1:子菜单,2:按钮}")
    private String menuType;

    /**
     * 图片
     */
    @Length(max = 100)
    @ApiModelProperty("图片")
    private String icon;

    /**
     * 组件
     */
    @Length(max = 100)
    @ApiModelProperty("组件")
    private String component;

    /**
     * 路径
     */
    @Length(max = 100)
    @ApiModelProperty("路径")
    private String path;

    /**
     * 排序
     */
    @Length(max = 100)
    @ApiModelProperty("排序")
    private String sort;

    /**
     * 类型{系统、租户}
     */
    @Length(max = 100)
    @ApiModelProperty("类型{系统、租户}")
    private String type;

    /**
     * 租户ID
     */
    @Length(max = 64)
    @ApiModelProperty("租户ID")
    private String tenantId;

}