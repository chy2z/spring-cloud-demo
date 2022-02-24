package org.chy.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataSourceContextHolder {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void setDataSource(String dbType) {
        if(DataSourceType.V_SLAVE.getName().equals(dbType)) {
            LOGGER.info("切换到[" + dbType + "]数据源");
        }
        contextHolder.set(dbType);
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}