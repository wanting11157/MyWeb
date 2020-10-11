package com.wanting.me.mapper;


import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CourseMapper {
    Integer add(Course course);
    Integer update(Course course);
    Integer del(int id);
    Course getById(int id);
    List<Course> search(Course course, int page, int rows);
    Integer countTotal(Course course, int page, int rows);
}
