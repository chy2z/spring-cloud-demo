package org.chy.carorder.service;

import org.chy.carorder.entity.Users;

import java.util.List;

/**
 * 原生mybatis
 *
 * Created by chy on 2021/8/11.
 */
public interface UserService {
    Users selectByPrimaryKey(Integer id);
    List<Users> selectAll();
}
