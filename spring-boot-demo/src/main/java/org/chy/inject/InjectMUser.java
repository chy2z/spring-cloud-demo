package org.chy.inject;

import java.lang.annotation.*;

/**
 * 方法参数解析注解
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectMUser {
}
