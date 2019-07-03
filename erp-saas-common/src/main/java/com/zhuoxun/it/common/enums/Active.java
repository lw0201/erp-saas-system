package com.zhuoxun.it.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum Active {

    /** Y */
    Y,
    /** N */
    N,
    /** NA */
    NA;

    public String getName() {
        return name();
    }

    private static Map<String, Active> codeLookup = new HashMap<>();
    static {
        for (Active active : Active.values()) {
            codeLookup.put(active.getName(), active);
        }
    }

    public static Active forCode(String Key) {
        return codeLookup.get(Key);
    }
}
