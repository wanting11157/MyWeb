package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
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
    public Integer add(Score score)throws Exception {


        // nn

        Integer add = scoreMapper.add(score);

        //




        return add;
    }

    @Override
    public Integer update(Score score)throws Exception {
        Integer update = scoreMapper.update(score);

        return update;
    }

    @Override
    public Integer del(int id)throws Exception {
        Integer del = scoreMapper.del(id);
        // todo .....
        return del;
    }

    @Override
    public Score getById(int id)throws Exception {
        Score score = scoreMapper.getById(id);
        return score;
    }

    @Override
    public List<Score> search(Score score, Integer page, Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        List<Score> scores = scoreMapper.search(score, start, rows);
        return scores;
    }

    @Override
    public Integer countTotal(Score score, Integer page, Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = scoreMapper.countTotal(score, start, rows);
        return total;
    }
}
