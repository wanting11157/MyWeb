package com.wanting.me.service;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;

import java.sql.SQLException;
import java.util.List;


public interface ScoreService {
    Integer add(Score score)throws Exception;
    Integer update(Score score)throws Exception;
    Integer save(Score score)throws Exception;
    Integer del(Score score)throws Exception;
    List<Score> getByCourseIds(Integer[] courseIds) throws Exception;
    List<Score> getByUserAndCourse(Integer courseId,Integer studentId)throws SQLException;
    Score getById(Integer id)throws Exception;
    List<Score> search(Score score, String courseName,Integer teacherId,
                       String teacherName,String studentName,Integer page, Integer rows)throws Exception;
    Integer countTotal(Score score, String courseName,Integer teacherId,
                       String teacherName,String studentName,Integer page, Integer rows)throws Exception;

    Integer getScore(Integer courseId, Integer studentId)throws Exception;

    List<Score> getByStuIds(Integer[] stuIds) throws Exception;

    Integer dels(Integer[] ids) throws Exception;;
}