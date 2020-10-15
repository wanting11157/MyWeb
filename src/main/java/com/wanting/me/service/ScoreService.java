package com.wanting.me.service;


import com.wanting.me.entity.Score;

import java.util.List;


public interface ScoreService {
    Integer add(Score score)throws Exception;
    Integer update(Score score)throws Exception;
    Integer del(int id)throws Exception;
    Score getById(Integer courseId,Integer studentId)throws Exception;
    List<Score> search(Score score)throws Exception;
    Integer countTotal(Score score, Integer page, Integer rows)throws Exception;
}