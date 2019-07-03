package com.zhuoxun.it.common.conditions;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序
 * 
 * @author liwen
 */
@Data
@NoArgsConstructor
public class Sort {

    /**
     * 选择要排序的列、属性，对应实体属性字段
     */
    @ApiModelProperty("排序的字段属性")
    private String attr;

    /**
     * 排序规则{ASC,DESC}
     */
    @ApiModelProperty("排序规则")
    private Order order;

    public Sort(String attr, Order order) {
        this.attr = attr;
        this.order = order;
    }

}
