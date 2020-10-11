package com.wanting.me.service.impl;

import com.wanting.me.entity.Course;
import com.wanting.me.entity.User;
import com.wanting.me.mapper.CourseMapper;
import com.wanting.me.mapper.UserMapper;
import com.wanting.me.service.CourseService;
import com.wanting.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Integer add(Course course) {


        // nn

        Integer add = courseMapper.add(course);

        //




        return add;
    }

    @Override
    public Integer update(Course course) {
        Integer update = courseMapper.update(course);

        return update;
    }

    @Override
    public Integer del(int id) {
        Integer del = courseMapper.del(id);
        // todo .....
        return del;
    }

    @Override
    public Course getById(int id) {
        Course course = courseMapper.getById(id);
        return course;
    }

    @Override
    public List<Course> search(Course course, int page, int rows) {
        List<Course> courses = courseMapper.search(course, page, rows);
        return courses;
    }

    @Override
    public Integer countTotal(Course course, Integer page, Integer rows) {
        Integer total = courseMapper.countTotal(course, page, rows);
        return total;
    }
}
