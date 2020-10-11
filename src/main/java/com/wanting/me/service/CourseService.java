package com.wanting.me.service;


import com.wanting.me.entity.Course;
import com.wanting.me.entity.User;

import java.util.List;


public interface CourseService {
    Integer add(Course course);
    Integer update(Course course);
    Integer del(int id);
    Course getById(int id);
    List<Course> search(Course course, int page, int rows);
    int countTotal(Course course, int page, int rows);
}