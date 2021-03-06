package com.wanting.me.service;


import com.wanting.me.entity.Course;
import com.wanting.me.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface CourseService {
    Integer add(Course course)throws Exception;
    Integer update(Course course)throws Exception;
    Integer del(int id)throws Exception;
    Course getById(int id)throws Exception;
    List<Course> search(Course course,Integer page ,Integer rows)throws Exception;
    Integer countTotal(Course course,Integer page ,Integer rows)throws Exception;

    Integer dels(Integer[] ids) throws Exception;

    Map importExcel(MultipartFile file) throws Exception;

    List<Course> searchAll() throws Exception;
}