package com.zhuoxun.it.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 租户状态
 * 
 * @author liwen
 */
public enum TenantState {

    /** Enable 启用 */
    Enable(0),
    /** Disble 禁用 */
    Disble(1),
    /** Expire 过期 */
    Expire(2);

    private int code;

    TenantState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name();
    }

    private static Map<Integer, TenantState> codeLookup = new HashMap<>();
    static {
        for (TenantState tenantState : TenantState.values()) {
            codeLookup.put(tenantState.getCode(), tenantState);
        }
    }

    public static TenantState forCode(int Key) {
        return codeLookup.get(Key);
    }

}
