package com.zhuoxun.it.iam.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Tenant 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    @NotBlank(groups = {Update.class})
    private String id;

    /**
     * 企业编码,系统自动生成
     */
    @Length(max = 100)
    @ApiModelProperty("企业编码,系统自动生成")
    private String tenantCode;

    /**
     * 企业名称
     */
    @Length(max = 100)
    @ApiModelProperty("企业名称")
    @NotBlank(groups = {Insert.class})
    private String tenantName;

    /**
     * 企业类型
     */
    @Length(max = 100)
    @ApiModelProperty("企业类型")
    private String tenantType;

    /**
     * 省
     */
    @Length(max = 100)
    @ApiModelProperty("省")
    private String province;

    /**
     * 市
     */
    @Length(max = 100)
    @ApiModelProperty("市")
    private String city;

    /**
     * 区
     */
    @Length(max = 100)
    @ApiModelProperty("区")
    private String area;

    /**
     * 详细地址
     */
    @Length(max = 255)
    @ApiModelProperty("详细地址")
    private String address;

    /**
     * 统一信用代码
     */
    @Length(max = 100)
    @ApiModelProperty("统一信用代码")
    @NotBlank(groups = {Insert.class})
    private String creditCode;

    /**
     * 联系人
     */
    @Length(max = 100)
    @ApiModelProperty("联系人")
    @NotBlank(groups = {Insert.class})
    private String userName;

    /**
     * 电话号码
     */
    @Length(max = 15)
    @ApiModelProperty("电话号码")
    private String phone;

    /**
     * 有效日期
     */
    @ApiModelProperty("有效日期")
    @NotNull(groups = {Insert.class})
    private Date effectiveDate;

    /**
     * 状态{0:正常、1:禁用、2:已过期}
     */
    @ApiModelProperty("状态{0:正常、1:禁用、2:已过期}")
    private Integer state;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    @NotBlank(groups = {Insert.class})
    private String password;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}