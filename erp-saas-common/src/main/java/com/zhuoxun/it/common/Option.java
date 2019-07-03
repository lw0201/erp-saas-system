package com.zhuoxun.it.common;

import lombok.Data;

/**
 * Option
 * 
 * @author liwen
 *
 */
@Data
public class Option {

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    public Option(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
