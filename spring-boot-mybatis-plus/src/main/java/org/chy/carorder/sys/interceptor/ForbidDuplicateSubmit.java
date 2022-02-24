package org.chy.carorder.sys.interceptor;


import java.lang.annotation.*;

/**
 * 防止重复提交
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ForbidDuplicateSubmit {

}
