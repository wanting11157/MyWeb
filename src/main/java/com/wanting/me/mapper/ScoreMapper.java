package com.wanting.me.mapper;


import com.wanting.me.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface ScoreMapper {
    Integer add(Score score)throws SQLException;
    Integer update(Score score)throws SQLException;
    Integer del(Score score)throws SQLException;
    Score getById(Integer id)throws SQLException;
    List<Score> search(Score score, Integer start, Integer rows)throws SQLException;
    Integer countTotal(Score score, Integer start, Integer rows)throws SQLException;
}
