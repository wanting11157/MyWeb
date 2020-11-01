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
    Integer dels(Integer[] ids)throws SQLException;
    User getById(Integer id)throws SQLException;
    List<User> search(User user,Integer start, Integer rows)throws SQLException;
    Integer countTotal(User user,Integer start, Integer rows)throws SQLException;


    List<User> getByHaoMa(String username)throws SQLException;

    void hasPhoto(Integer id)throws SQLException;

    List<User> searchAll()throws SQLException;
}
