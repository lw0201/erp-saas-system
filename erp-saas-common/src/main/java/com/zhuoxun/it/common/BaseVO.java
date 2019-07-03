package com.zhuoxun.it.common;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础值对象
 * 
 * @author liwen
 */
@Data
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    @Length(max = 64)
    @ApiModelProperty("创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createdDate;

    /**
     * 最后修改人
     */
    @Length(max = 64)
    @ApiModelProperty("最后修改人")
    private String lastUpdateBy;

    /**
     * 最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    private Date lastUpdateDate;
}
