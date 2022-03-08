package org.chy.carorder.config;

import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 */
@Configuration
@EnableCaching
public class RedissonCacheConfig {

  /**
   * 车辆订单信息缓存key前缀
   */
  public static final String CACHE_CAR_ORDER_INFO = "CHY:CAR-ORDER:CAR-NO:all";

  @Bean
  public CacheManager cacheManager(RedissonClient redissonClient) {
    Map<String, CacheConfig> config = new HashMap<>(4);
    // 缓存1天，空闲：12小时删除
    //config.put(CACHE_CAR_ORDER_INFO, new CacheConfig(24 * 60 * 60 * 1000, 12 * 60 * 60 * 1000));
    // 缓存1时，空闲：1小时删除
    //config.put(CACHE_CAR_ORDER_INFO, new CacheConfig(1 * 60 * 60 * 1000, 1 * 60 * 60 * 1000));
    // 缓存1时，空闲：5 分钟删除
    config.put(CACHE_CAR_ORDER_INFO, new CacheConfig(1 * 60 * 60 * 1000, 5 * 60 * 1000));
    return new RedissonSpringCacheManager(redissonClient, config, new JsonJacksonCodec());
  }
}