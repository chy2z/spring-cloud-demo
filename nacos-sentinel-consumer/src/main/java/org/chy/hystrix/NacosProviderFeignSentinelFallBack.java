package org.chy.hystrix;

import com.alibaba.csp.sentinel.context.ContextUtil;
import org.chy.services.NacosProviderFeignClientApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 失败熔断
 *
 * Created by chy on 2021/7/16.
 */
@Component
public class NacosProviderFeignSentinelFallBack implements NacosProviderFeignClientApi {
    final static Logger logger = LoggerFactory.getLogger(NacosProviderFeignSentinelFallBack.class);

    @Override
    public String fetchConfig(String str) {
        // 异常打印
        logger.error("fallback: ", ContextUtil.getContext().getCurEntry().getError());
        return "fall back fetchConfig";
    }

    @Override
    public String version() {
        // 异常打印
        logger.error("fallback: ", ContextUtil.getContext().getCurEntry().getError());
        return "fall back version";
    }
}
