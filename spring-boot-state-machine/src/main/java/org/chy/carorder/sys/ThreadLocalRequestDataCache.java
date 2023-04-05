package org.chy.carorder.sys;

import javax.servlet.ServletRequest;

/**
 * 线程共享
 * Created by chy on 2021/11/16.
 */
public class ThreadLocalRequestDataCache {
    public static ThreadLocal<ServletRequest> httpRequestThreadLocal = new ThreadLocal<>();

    public static void set(ServletRequest httpRequestHeader) {
        httpRequestThreadLocal.set(httpRequestHeader);
    }

    public static ServletRequest get() {
        return httpRequestThreadLocal.get();
    }

    public static void remove() {
        httpRequestThreadLocal.remove();
    }
}