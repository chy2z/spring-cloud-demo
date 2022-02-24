package org.chy.carorder.controller;

import org.chy.services.NacosProviderFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chy on 2021/7/16.
 */
@RestController
public class ConsumerController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NacosProviderFeignService nacosProviderFeignService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * http://localhost:9090/rest/test
     *
     * @param str
     * @return
     */
    @GetMapping("/rest/{str}")
    public String rest(@PathVariable String str,HttpServletRequest request) {
        printHeader(request);
        return restTemplate.getForObject("http://nacos-provider/getConfig/" + str,
                String.class);
    }

    /**
     * http://localhost:9090/feign-version
     *
     * @return
     */
    @GetMapping("/feign-version")
    public String version() {
        return nacosProviderFeignService.version();
    }

    /**
     * http://localhost:9090/feign-fetchConfig/test
     *
     * @param str
     * @return
     */
    @GetMapping("/feign-fetchConfig/{str}")
    public String fetchConfig(@PathVariable String str,HttpServletRequest request) {
        logger.info("当前线程ID：" + Thread.currentThread().getId() + "当前线程Name" + Thread.currentThread().getName());
        printHeader(request);
        return nacosProviderFeignService.fetchConfig(str);
    }

    /**
     * http://localhost:9090/discovery/services/nacos-provider
     * http://localhost:9090/discovery/services/nacos-hystrix-consumer
     *
     * @param service
     * @return
     */
    @GetMapping("/discovery/services/{service}")
    public Object client(@PathVariable String service) {
        return discoveryClient.getInstances(service);
    }

    /**
     * http://localhost:9090/discovery/services
     *
     * @return
     */
    @GetMapping("/discovery/services")
    public Object services() {
        return discoveryClient.getServices();
    }
}