package com.wanting.me.service.impl;

import com.wanting.me.entity.Knowledge;
import com.wanting.me.mapper.KnowledgeMapper;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    public List<Knowledge> start() throws Exception {
        return knowledgeMapper.start();
    }

    @Override
    public Integer add(Integer parentId) throws Exception {
        return knowledgeMapper.add(parentId);
    }

    @Override
    public Integer submit(Knowledge knowledge) throws Exception {
        return knowledgeMapper.submit(knowledge);
    }

    @Override
    public Integer update(Knowledge knowledge) throws Exception {
        return knowledgeMapper.update(knowledge);
    }

    @Override
    public Integer dels(Integer[] ids) throws Exception {
        return knowledgeMapper.dels(ids);
    }

    @Override
    public Integer genAdd() throws Exception {
        return knowledgeMapper.genAdd();
    }
}
