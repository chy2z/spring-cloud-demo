package org.chy.carorder.sys;

import org.chy.carorder.config.RequestConfig;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 方法返回通知
 * Created by chy on 2021/11/16.
 */
@RestControllerAdvice(annotations = RestController.class)
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseEntityDTO) {
            HttpServletRequest servletRequest = (HttpServletRequest) ThreadLocalRequestDataCache.get();
            long startTime = Long.valueOf(servletRequest.getAttribute(RequestConfig.START_TIME).toString());
            long endTime = System.currentTimeMillis();
            ResponseEntityDTO responseEntity = (ResponseEntityDTO) body;
            responseEntity.setCost(endTime - startTime);
        }
        return body;
    }
}