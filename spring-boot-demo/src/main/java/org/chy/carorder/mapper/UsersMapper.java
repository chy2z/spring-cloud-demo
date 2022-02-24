package org.chy.carorder.mapper;

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

    List<Users> selectAll();

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);
}