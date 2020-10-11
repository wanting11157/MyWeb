package com.wanting.me.mapper;


import com.wanting.me.entity.Score;

import com.wanting.me.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ScoreMapper {
    Integer add(Score score);
    Integer update(Score score);
    Integer del(int id);
    Score getById(int id);
    List<Score> search(Score score, int page, int rows);
    Integer countTotal(Score score, Integer page, Integer rows);
}
