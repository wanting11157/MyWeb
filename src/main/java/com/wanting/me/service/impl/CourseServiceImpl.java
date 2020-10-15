package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.entity.Course;
import com.wanting.me.mapper.CourseMapper;
import com.wanting.me.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Integer add(Course course) throws Exception{


        // nn

        Integer add = courseMapper.add(course);

        //




        return add;
    }

    @Override
    public Integer update(Course course)throws Exception {
        Integer update = courseMapper.update(course);

        return update;
    }

    @Override
    public Integer del(int id)throws Exception {
        Integer del = courseMapper.del(id);
        // todo .....
        return del;
    }

    @Override
    public Course getById(int id) throws Exception{
        Course course = courseMapper.getById(id);
        return course;
    }

    @Override
    public List<Course> search(Course course) throws Exception{
//        int start = ResponsePage.initStart(page,rows);
        List<Course> courses = courseMapper.search(course);
        return courses;
    }

    @Override
    public Integer countTotal(Course course, Integer page, Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = courseMapper.countTotal(course, start, rows);
        return total;
    }
}
