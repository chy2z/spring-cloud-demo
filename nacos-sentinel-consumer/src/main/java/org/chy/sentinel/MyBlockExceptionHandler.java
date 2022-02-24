package org.chy.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.AbstractSentinelInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  sentinel 限流返回结果
 *
 *  重写 Blocked by Sentinel (flow limiting) 返回结果
 *
 *  @see  AbstractSentinelInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    final static Logger logger = LoggerFactory.getLogger(MyBlockExceptionHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        logger.info(request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString("限流"));
    }
}