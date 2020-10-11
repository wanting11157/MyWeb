package com.wanting.me.service.impl;

import com.wanting.me.entity.User;
import com.wanting.me.mapper.UserMapper;
import com.wanting.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public Integer add(User user) {


        // nn

        Integer add = userMapper.add(user);

        //




        return add;
    }

    @Override
    public Integer update(User user) {
        Integer update = userMapper.update(user);

        return update;
    }

    @Override
    public Integer del(int id) {
        Integer del = userMapper.del(id);
        // todo .....
        return del;
    }

    @Override
    public User getById(int id) {
        User user = userMapper.getById(id);
        return user;
    }

    @Override
    public List<User> search(User user, int page, int rows) {
        List<User> users = userMapper.search(user, page, rows);
        return users;
    }

    @Override
    public Integer countTotal(User user, Integer page, Integer rows) {
        Integer total = userMapper.countTotal(user, page, rows);
        return total;
    }
}
