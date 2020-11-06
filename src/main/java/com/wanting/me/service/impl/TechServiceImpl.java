package com.wanting.me.service.impl;

import com.wanting.me.mapper.TechMapper;
import com.wanting.me.mapper.UserMapper;
import com.wanting.me.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechServiceImpl implements TechService {

    @Autowired
    private TechMapper techMapper;

    @Override
    public Integer save(String content) throws Exception {
        return techMapper.save(content);
    }

    @Override
    public String show() throws Exception {
        return techMapper.show();
    }
}
