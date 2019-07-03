package com.zhuoxun.it.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserType {

    /** 系统用户 */
    System(1),
    /** 租户用户 */
    Tenant(2),
    /** 普通用户 */
    Guest(3);

    private int code;

    UserType(int code) {
        this.code = code;
    }

    public String getName() {
        return name();
    }

    public int getCode() {
        return code;
    }

    private static Map<Integer, UserType> codeLookup = new HashMap<>();
    static {
        for (UserType userType : UserType.values()) {
            codeLookup.put(userType.getCode(), userType);
        }
    }

    public static UserType forCode(Integer Key) {
        return codeLookup.get(Key);
    }

}
