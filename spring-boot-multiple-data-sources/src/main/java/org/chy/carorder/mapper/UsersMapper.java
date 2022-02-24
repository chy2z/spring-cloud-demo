package org.chy.carorder.mapper;

import org.chy.datasource.DataSourceType;
import org.chy.datasource.annotation.DataSource;
import org.chy.carorder.entity.Users;

import java.util.List;

/**
 * 原生mybatis
 *
 * Created by chy on 2021/8/11.
 */
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Users record);

    /**
     * 从 从库读取数据
     * @return
     */
    @DataSource(value = DataSourceType.V_SLAVE)
    List<Users> selectAll();

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);
}