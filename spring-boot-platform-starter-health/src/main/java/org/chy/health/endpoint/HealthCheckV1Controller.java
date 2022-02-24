package org.chy.health.endpoint;


import org.chy.health.support.HealthProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 不要被其他项目扫描到 Controller
 * 需要通过自动装配 spring.factories 文件, 执行 AppHealthAutoConfiguration 注入 HealthCheckV1Controller
 */
@RestController
public class HealthCheckV1Controller {
    private final HealthProperties healthProperties;

    public HealthCheckV1Controller(HealthProperties healthProperties) {
        this.healthProperties = healthProperties;
    }

    @GetMapping("/chy/app/ping")
    public String ping() {
        return this.healthProperties.getResponse();
    }
}