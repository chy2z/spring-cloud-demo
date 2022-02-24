package org.chy.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.springframework.stereotype.Component;

/**
 * sentinel 资源名
 */
@Component
public class MyUrlCleaner implements UrlCleaner {

    /**
     * 重写资源名称
     * @param originUrl
     * @return
     */
    @Override
    public String clean(String originUrl) {
        return originUrl;
    }
}