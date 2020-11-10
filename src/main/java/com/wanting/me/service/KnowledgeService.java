package com.wanting.me.service;

import com.wanting.me.entity.Knowledge;

import java.util.List;

public interface KnowledgeService {
    List<Knowledge> start() throws Exception;

    Integer add(Integer parentId) throws Exception;

    Integer submit(Knowledge knowledge) throws Exception;

    Integer update(Knowledge knowledge) throws Exception;

    Integer dels(Integer[] ids) throws Exception;

    Integer genAdd() throws Exception;
}
