package org.chy.annotation;

import java.lang.annotation.*;

/**
 * @author chy
 * @Title: 忽略参数的打印
 * @Description:
 * @date 2022/9/21 21:58
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintLogIgnore {

}
