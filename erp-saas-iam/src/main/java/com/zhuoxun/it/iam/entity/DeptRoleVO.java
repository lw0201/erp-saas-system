package com.zhuoxun.it.iam.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DeptRole 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptRoleVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 部门ID
     */
    @Length(max = 100)
    @ApiModelProperty("部门ID")
    private String deptId;

    /**
     * 角色ID
     */
    @Length(max = 100)
    @ApiModelProperty("角色ID")
    private String roleId;

}