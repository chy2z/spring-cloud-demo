package org.chy.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态确定使用那个数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获取数据源的key
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}