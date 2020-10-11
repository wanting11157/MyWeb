package com.wanting.me.service.impl;

import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.mapper.CourseMapper;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.service.CourseService;
import com.wanting.me.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {


    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public Integer add(Score score) {


        // nn

        Integer add = scoreMapper.add(score);

        //




        return add;
    }

    @Override
    public Integer update(Score score) {
        Integer update = scoreMapper.update(score);

        return update;
    }

    @Override
    public Integer del(int id) {
        Integer del = scoreMapper.del(id);
        // todo .....
        return del;
    }

    @Override
    public Score getById(int id) {
        Score score = scoreMapper.getById(id);
        return score;
    }

    @Override
    public List<Score> search(Score score, Integer page, Integer rows) {
        List<Score> scores = scoreMapper.search(score, page, rows);
        return scores;
    }

    @Override
    public Integer countTotal(Score score, Integer page, Integer rows) {
        Integer total = scoreMapper.countTotal(score, page, rows);
        return total;
    }
}
