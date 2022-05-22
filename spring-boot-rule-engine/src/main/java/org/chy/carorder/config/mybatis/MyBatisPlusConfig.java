package org.chy.carorder.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

  /**
   * 自动填充
   *
   * @return
   */
  @Bean
  public MetaObjectHandler metaObjectHandler() {
    return new CommonMetaObjectHandler();
  }


  /**
   * 分页插件
   *
   * @return
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    // 分页插件
    PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
    paginationInnerInterceptor.setDbType(DbType.MYSQL);
    paginationInnerInterceptor.setOverflow(false);
    interceptor.addInnerInterceptor(paginationInnerInterceptor);
    return interceptor;
  }

}
