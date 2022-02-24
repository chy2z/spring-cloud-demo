package org.chy.services;

import org.chy.hystrix.NacosProviderFeignSentinelFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign客户端
 *
 * Created by chy on 2021/7/16.
 */
@Component
@FeignClient(name = "nacos-provider",fallback = NacosProviderFeignSentinelFallBack.class)
public interface NacosProviderFeignService {

    @GetMapping("/getConfig/{msg}")
    String fetchConfig(@PathVariable("msg") String msg);


    @GetMapping("/version")
    String version();
}
