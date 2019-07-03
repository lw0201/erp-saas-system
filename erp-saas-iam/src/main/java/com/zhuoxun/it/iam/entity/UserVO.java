package com.zhuoxun.it.iam.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.zhuoxun.it.common.BaseVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User 实体类
 * 
 * @author liwen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Length(max = 64)
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 用户账号,系统自动生成
     */
    @Length(max = 100)
    @ApiModelProperty("用户账号,系统自动生成")
    private String userAccount;

    /**
     * 用户的真实姓名
     */
    @Length(max = 100)
    @ApiModelProperty("用户的真实姓名")
    private String realName;

    /**
     * 用户手机号码
     */
    @Length(max = 20)
    @ApiModelProperty("用户手机号码")
    private String phone;

    /**
     * 用户密码
     */
    @Length(max = 255)
    @ApiModelProperty("用户密码")
    private String password;

    /**
     * 性別
     */
    @Length(max = 1)
    @ApiModelProperty("性別")
    private String sex;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private Date birthDate;

    /**
     * 用户类型{1：系统用户，2：租户用户，3普通用户}
     */
    @ApiModelProperty("用户类型{1：系统用户，2：租户用户，3普通用户}")
    private Integer userType;

    /**
     * 数据查看范围(暂时不做)
     */
    @ApiModelProperty("数据查看范围(暂时不做)")
    private Integer dataRange;

    /**
     * 状态{0:正常、1:禁用、2:已过期}
     */
    @ApiModelProperty("状态{0:正常、1:禁用、2:已过期}")
    private Integer state;


}