package com.wanting.me.service;


import com.wanting.me.entity.User;

import java.util.List;


public interface UserService {
    Integer add(User user);
    Integer update(User user);
    Integer del(int id);
    User getById(int id);
    List<User> search(User user, Integer page, Integer rows);
    Integer countTotal(User user, Integer page, Integer rows);
}