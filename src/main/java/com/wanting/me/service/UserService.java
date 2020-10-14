package com.wanting.me.service;


import com.wanting.me.entity.User;

import java.util.List;


public interface UserService {
    Integer add(User user)throws Exception;
    Integer update(User user)throws Exception;
    Integer del(int id)throws Exception;
    User getById(int id)throws Exception;
    List<User> search(User user, Integer page, Integer rows)throws Exception;
    Integer countTotal(User user, Integer page, Integer rows)throws Exception;

    String login(String username, String password)throws Exception;
}