package com.wanting.me.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface TechMapper {
    Integer save(String content) throws SQLException;

    String show() throws SQLException;
}
