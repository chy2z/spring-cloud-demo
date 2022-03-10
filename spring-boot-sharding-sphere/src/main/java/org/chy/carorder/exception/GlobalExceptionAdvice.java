package org.chy.carorder.exception;

import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.entity.response.SysResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 全局异常统一处理
 * Created by chy on 2021/11/16.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {
    private static final String LOG_TAG = "[methodException] [异常信息]:{}";
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntityDTO<String> handleException(Exception e, HttpServletRequest request) {
        LOGGER.error(LOG_TAG, request.getRequestURI(), e);
        if (Objects.nonNull(e.getCause())) {
            return ResponseEntityDTO.failure(SysResponseCode.FAILED.getCode(), e.getCause().getMessage());
        } else {
            return ResponseEntityDTO.failure(SysResponseCode.FAILED.getCode(), e.getMessage());
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntityDTO<String> handleException(MissingServletRequestParameterException e,HttpServletRequest request) {
        LOGGER.error(LOG_TAG, request.getRequestURI(), e);
        return ResponseEntityDTO.failure(SysResponseCode.PARAMETER_FAILED.setArgs(e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntityDTO<String> handleException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        LOGGER.error(LOG_TAG, request.getRequestURI(), e);
        return ResponseEntityDTO.failure(SysResponseCode.METHOD_NOT_SUPPORT);
    }
}