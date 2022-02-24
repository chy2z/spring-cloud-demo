package org.chy.carorder.sys.filter;


import org.chy.carorder.config.RequestConfig;
import org.chy.carorder.sys.ThreadLocalRequestDataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统计请求耗时时间
 * 对同一个请求，只经过一次过滤
 * Created by chy on 2021/11/16.
 */
public class TimeFilter extends OncePerRequestFilter {
    private final static Logger LOGGER = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            request.setAttribute(RequestConfig.START_TIME, String.valueOf(System.currentTimeMillis()));
            ThreadLocalRequestDataCache.set(request);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            LOGGER.error("TimeFilter",e);
        } finally {
            ThreadLocalRequestDataCache.remove();
        }
    }
}