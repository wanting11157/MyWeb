package com.wanting.me.mapper;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.dto.ScoreDto;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface ScoreMapper {
    Integer add(Score score)throws SQLException;
    Integer update(Score score)throws SQLException;
    Integer del(Score score)throws SQLException;
    Score getById(Integer id)throws SQLException;
    List<Score> getByCourseIds(Integer[] courseIds)throws SQLException;
    List<Score> getByUserAndCourse(Integer courseId,Integer studentId)throws SQLException;
    List<ScoreDto> search(Score score, Integer start, Integer rows)throws SQLException;
    Integer countTotal(Score score,Integer start, Integer rows)throws SQLException;

    Integer getScore(Integer courseId, Integer studentId)throws SQLException;

    List<Score> getByStuIds(Integer[] stuIds) throws SQLException;

    Integer dels(List<Score> scores) throws SQLException;

    Integer delByIds(Integer[] ids) throws SQLException;

    List<Score> checkrepeatscore(Score score) throws SQLException;
}
