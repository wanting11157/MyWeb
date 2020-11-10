package com.wanting.me.mapper;

import com.wanting.me.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface KnowledgeMapper {
    List<Knowledge> start() throws SQLException;

    Integer add(Integer parentId) throws SQLException;

    Integer submit(Knowledge knowledge) throws SQLException;

    Integer update(Knowledge knowledge) throws SQLException;

    Integer dels(Integer[] ids) throws SQLException;

    Integer genAdd() throws SQLException;
}
