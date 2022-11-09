package org.chy.hystrix;

import org.chy.services.NacosProviderFeignClientApi;
import org.springframework.stereotype.Component;

/**
 * 失败熔断
 *
 * Created by chy on 2021/7/16.
 */
@Component
public class NacosProviderFeignSentinelFallBack implements NacosProviderFeignClientApi {

    @Override
    public String fetchConfig(String str) {
        return "fall back fetchConfig";
    }

    @Override
    public String version() {
        return "fall back version";
    }
}
