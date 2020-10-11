package com.wanting.me.mapper;


import com.wanting.me.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    Integer add(User user);
    Integer update(User user);
    Integer del(int id);
    User getById(int id);
    List<User> search(User user, int page, int rows);
    Integer countTotal(User user, Integer page, Integer rows);
}
