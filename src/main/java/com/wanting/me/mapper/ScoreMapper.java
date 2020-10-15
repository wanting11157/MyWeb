package com.wanting.me.mapper;


import com.wanting.me.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface ScoreMapper {
    Integer add(Score score)throws SQLException;
    Integer update(Score score)throws SQLException;
    Integer del(int id)throws SQLException;
    Score getById(Integer courseId,Integer studentId)throws SQLException;
    List<Score> search(Score score)throws SQLException;
    Integer countTotal(Score score, Integer start, Integer rows)throws SQLException;
}
