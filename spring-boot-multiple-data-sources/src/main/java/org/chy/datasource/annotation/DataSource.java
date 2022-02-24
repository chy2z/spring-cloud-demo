package org.chy.datasource.annotation;


import org.chy.datasource.DataSourceType;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DataSourceType value() default DataSourceType.DEFAULT;
}
