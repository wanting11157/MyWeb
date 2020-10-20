package com.wanting.me.service;


import com.wanting.me.entity.User;

import java.util.List;


public interface UserService {
    Integer add(User user)throws Exception;
    Integer update(User user)throws Exception;
    Integer del(int id)throws Exception;
    Integer dels(Integer[] ids)throws Exception;
    User getById(Integer id)throws Exception;
    List<User> search(Integer page, Integer rows)throws Exception;
    Integer countTotal()throws Exception;

    String login(String username, String password)throws Exception;
}