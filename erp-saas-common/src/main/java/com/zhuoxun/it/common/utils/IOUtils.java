package com.zhuoxun.it.common.utils;

import java.io.Closeable;

/**
 * IO工具类
 * 
 * @author liwen
 *
 */
public class IOUtils {

    /**
     * 关闭流
     * 
     * @param x
     *            Closeable
     */
    public static void close(Closeable x) {
        if (x != null) {
            try {
                x.close();
            } catch (Exception e) {
                // skip
            }
        }
    }

}
