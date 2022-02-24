package org.chy.interceptor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * RestTemplate的请求进行拦截 追加请求头
 *
 * Created by chy on 2021/8/21.
 */
@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);
    private final String tokenHeader = "x-token";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        ServletRequestAttributes servletAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletAttributes.getRequest();
        String token = httpServletRequest.getHeader(tokenHeader);
        HttpHeaders headers = request.getHeaders();
        logger.info("tokenHeader:{}---value:{}",tokenHeader,token);
        if (StringUtils.isNotBlank(token)) {
            headers.add(tokenHeader, token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.toString());
        }
        return execution.execute(request, body);
    }
}