package com.wanting.me.mapper;


import com.wanting.me.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface UserMapper {
    Integer add(User user)throws SQLException;
    Integer update(User user)throws SQLException;
    Integer del(int id)throws SQLException;
    User getById(Integer id)throws SQLException;
    List<User> search(User user, int start, int rows)throws SQLException;
    Integer countTotal(User user, Integer start, Integer rows)throws SQLException;


    List<User> getByHaoMa(String username)throws SQLException;
}
