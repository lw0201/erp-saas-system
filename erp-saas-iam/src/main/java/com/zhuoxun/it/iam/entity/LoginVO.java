
package com.zhuoxun.it.iam.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户账号,系统自动生成
     */
    @Length(max = 100)
    private String userAccount;

    /**
     * 用户的真实姓名
     */
    @Length(max = 100)
    private String realName;

    /**
     * 用户手机号码
     */
    @Length(max = 20)
    private String phone;

    /**
     * 性別
     */
    private Character sex;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 最后修改时间
     */
    private Date lastUpdateDate;

}
