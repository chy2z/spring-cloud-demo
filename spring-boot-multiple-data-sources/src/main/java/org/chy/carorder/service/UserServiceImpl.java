package org.chy.carorder.service;

import org.chy.carorder.mapper.UsersMapper;
import org.chy.carorder.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 原生mybatis
 *
 * Created by chy on 2021/8/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public List<Users> selectAll() {
        return usersMapper.selectAll();
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }
}