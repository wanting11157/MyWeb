package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.mapper.CourseMapper;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ScoreMapper scoreMapper;


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

        Course course = courseMapper.getById(id);

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
    public List<Course> search(Course course,Integer page ,Integer rows) throws Exception{
        Integer start = ResponsePage.initStart(page,rows);
        List<Course> courses = courseMapper.search(course,start,rows);
        return courses;
    }

    @Override
    public Integer countTotal(Course course,Integer page ,Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = courseMapper.countTotal(course,start,rows);
        return total;
    }

    @Override
    public Integer dels(Integer[] ids) throws Exception {
        //查有没有成绩
        Integer del;

        List<Score> scores = scoreMapper.getByCourseIds(ids);

        if(scores != null && scores.size()>0) {
            Integer a=scoreMapper.dels(scores);
            if(a==scores.size()) {
                del=courseMapper.dels(ids);
            } else{
                log.error("删除score表中courseid为："+ids+"失败");
                del =null;
            }
        }else {
            del = courseMapper.dels(ids);
        }


        return del;

    }
}
