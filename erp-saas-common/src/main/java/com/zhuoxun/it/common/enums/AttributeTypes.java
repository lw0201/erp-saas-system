package com.zhuoxun.it.common.enums;

public enum AttributeTypes {

    Text("文本"), /*文本*/
    Number("数字"), /* 数字*/
    Enum("枚举");/*枚举*/
    // NumberRange("数字范围"), /*数字范围*/
    // Color("成色"); /*成色*/

    private String name;

    AttributeTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.name();
    }

    public AttributeTypes[] vauless() {
        return AttributeTypes.values();
    }

}
