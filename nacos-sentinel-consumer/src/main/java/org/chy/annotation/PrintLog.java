package org.chy.annotation;

import org.springframework.boot.logging.LogLevel;

import java.lang.annotation.*;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/9/21 21:56
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintLog {
    /**
     * 功能id，例如用户管理的功能的id
     *
     * @return
     */
    String funId() default "";

    /**
     * 操作类型，例如新增，删除。修改
     *
     * @return
     */
    String operate() default "";

    /**
     * 描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 其他需要打印的参数
     *
     * @return
     */
    String args() default "";

    /**
     * 日志级别
     *
     * @return
     */
    LogLevel level() default LogLevel.INFO;
}
