package com.wanting.me.service;


import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;

import java.util.List;


public interface ScoreService {
    Integer add(Score score);
    Integer update(Score score);
    Integer del(int id);
    Score getById(int id);
    List<Score> search(Score score, Integer page, Integer rows);
    Integer countTotal(Score score, Integer page, Integer rows);
}