package org.chy.carorder.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 对FeignClient请求拦截，追加请求头
 *
 * Created by chy on 2021/8/21.
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(FeignConfig.class);

    private final String tokenHeader = "x-token";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader(tokenHeader);
            if (StringUtils.isNotBlank(token)) {
                requestTemplate.header(tokenHeader, token);
                logger.info("header:" + requestTemplate.headers());
            }
        }
    }
}