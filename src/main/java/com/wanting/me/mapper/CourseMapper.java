package com.wanting.me.mapper;


import com.wanting.me.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface CourseMapper {
    Integer add(Course course)throws SQLException;
    Integer update(Course course)throws SQLException;
    Integer del(int id)throws SQLException;
    Course getById(int id)throws SQLException;
    List<Course> search(Course course,Integer start,Integer rows)throws SQLException;
    Integer countTotal(Course course,Integer start,Integer rows)throws SQLException;

    Integer dels(Integer[] ids) throws SQLException;

    List<Course> searchAll()throws SQLException;
}
