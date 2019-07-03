package com.zhuoxun.it.common.conditions;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "条件构造器")
public class Wrapper<T> {

    @JsonIgnore
    private T entity;

    @ApiModelProperty("排序字段")
    private List<Sort> sorts;

    @ApiModelProperty("构造器")

    private List<Criteria> criterias;

    public Wrapper() {
        criterias = new ArrayList<Criteria>();
    }

    @JsonIgnore
    public void addSort(Sort sort) {
        if (null == sorts) {
            sorts = new ArrayList<Sort>();
        }
        if (null != sort) {
            sorts.add(sort);
        }
    }

    public Wrapper<T> or(Criteria criteria) {
        if (null != criteria) {
            criterias.add(criteria);
        }
        return this;
    }

    public Wrapper<T> where(Criteria criteria) {
        if (null != criteria) {
            criterias.add(criteria);
        }
        return this;
    }

    public void clear() {
        criterias.clear();
    }

}