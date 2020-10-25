package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;
import com.wanting.me.mapper.CourseMapper;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.service.CourseService;
import com.wanting.me.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {


    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public Integer add(Score score)throws Exception {

        Integer add;
         List<Score> scores = scoreMapper.checkrepeatscore(score);
         if(scores!=null && scores.size()>0){
             add = null;
         }else
             add=scoreMapper.add(score);;

        return add;
    }

    @Override
    public Integer update(Score score)throws Exception {
        Integer update = scoreMapper.update(score);

        return update;
    }

    @Override
    public Integer save(Score score) throws Exception {

        if(score != null){
            if(score.getId() == null){
                return  this.add(score);
            }else {
                return this.update(score);
            }
        }
        return null;
    }


    @Override
    public Integer del(Score score)throws Exception {
        Integer del = scoreMapper.del(score);

        return del;
    }

    @Override
    public List<Score> getByCourseIds(Integer[] courseIds) throws Exception {
        List<Score> scores = scoreMapper.getByCourseIds(courseIds);
        return scores;
    }

    @Override
    public List<Score> getByUserAndCourse(Integer courseId, Integer studentId) throws SQLException {
        return scoreMapper.getByUserAndCourse(courseId,studentId);
    }

    @Override
    public Score getById(Integer id)throws Exception {
        Score score = scoreMapper.getById(id);
        return score;
    }

    @Override
    public List<Score> search(Score score, String courseName,Integer teacherId,
                              String teacherName,String studentName,Integer page, Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        List<Score> scores = scoreMapper.search(score,courseName,teacherId,teacherName,
                studentName,start,rows);
        return scores;
    }

    @Override
    public Integer countTotal(Score score, String courseName,Integer teacherId,
                              String teacherName,String studentName,Integer page, Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = scoreMapper.countTotal(score,courseName,teacherId,teacherName,
                studentName,start,rows);
        return total;
    }

    @Override
    public Integer getScore(Integer courseId, Integer studentId) throws Exception {
        return scoreMapper.getScore(courseId,studentId);
    }

    @Override
    public List<Score> getByStuIds(Integer[] stuIds) throws Exception {
        return scoreMapper.getByStuIds(stuIds);
    }

    @Override
    public Integer dels(Integer[] ids) throws Exception {
        return scoreMapper.delByIds(ids);
    }
}
