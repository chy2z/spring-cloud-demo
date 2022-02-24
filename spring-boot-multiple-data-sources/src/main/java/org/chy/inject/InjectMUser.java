package org.chy.inject;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectMUser {
}
