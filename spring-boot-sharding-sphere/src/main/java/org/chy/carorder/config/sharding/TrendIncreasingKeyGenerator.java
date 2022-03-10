package org.chy.carorder.config.sharding;


import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 自定义趋势递增的Sharding key生成器
 */
public class TrendIncreasingKeyGenerator implements ShardingKeyGenerator {
    private final static Logger LOGGER = LoggerFactory.getLogger(TrendIncreasingKeyGenerator.class);

    private Properties properties;
    @Override
    public Comparable<?> generateKey() {
        // 模拟返回分布式id
        Long id = System.currentTimeMillis();
        LOGGER.info("TrendIncreasingKeyGenerator id:{}", id);
        return id;
    }

    @Override
    public String getType() {
        return "TREND_INCREASING";
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
