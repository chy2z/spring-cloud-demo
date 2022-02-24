package org.chy.carorder.config.mybatis;

/**
 * Created by Flex.Zang on 2021/9/15 22:58 数据库常用字段
 */
public interface DbFieldConstants {

  /**
   * 主键id的字段名
   */
  String ID = "id";

  /**
   * 创建用户的字段名
   */
  String CREATE_USER = "createUser";

  /**
   * 创建时间的字段名
   */
  String CREATE_TIME = "createTime";

  /**
   * 更新用户的字段名
   */
  String UPDATE_USER = "updateUser";

  /**
   * 更新时间的字段名
   */
  String UPDATE_TIME = "updateTime";

  /**
   * 删除标记的字段名
   */
  String DEL_FLAG = "delFlag";
}
