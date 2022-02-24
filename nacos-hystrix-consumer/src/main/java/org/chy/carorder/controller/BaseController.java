package org.chy.carorder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by chy on 2021/8/21.
 */
public class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public void printHeader(HttpServletRequest request){
        //获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            //根据名称获取请求头的值
            String value = request.getHeader(name);
            logger.info("request header:{}---value:{}", name, value);
        }
    }
}
