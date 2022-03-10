package org.chy.carorder.config.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Objects;

/**
 * sharding-jdbc的自定义分片算法
 */
public class DefaultPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        String suffix = genderToTableSuffix(preciseShardingValue.getValue(), collection.size());
        for (String each : collection) {
            if (each.endsWith(suffix)) {
                return each;
            }
        }
        throw new IllegalArgumentException("分表字段无法路由到分表:" + preciseShardingValue.getValue());
    }

    private String genderToTableSuffix(Long code, int tableNum) {
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("分表字段不能为空");
        }
        // 解决hasCode为Integer.MIN_VALUE,Math.abs仍然为负数的问题
        int hashCode = code.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            hashCode = Integer.MIN_VALUE + 1;
        }
        return String.valueOf(Math.abs(hashCode) % tableNum);
    }
}
