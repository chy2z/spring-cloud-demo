package org.chy.carorder.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;
import org.chy.carorder.controller.CarOrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 数据库表通用字段处理
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {

  private final static Logger LOGGER = LoggerFactory.getLogger(CarOrderController.class);

  @Override
  public void insertFill(MetaObject metaObject) {

    try {
      //setFieldValByName(CREATE_TIME, new Date(), metaObject);
    } catch (ReflectionException e) {
      LOGGER.warn("CommonMetaObjectHandler处理过程中无相关字段，不做处理");
    }
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    try {
      //setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    } catch (ReflectionException e) {
      LOGGER.warn("CommonMetaObjectHandler处理过程中无相关字段，不做处理");
    }
  }
}
