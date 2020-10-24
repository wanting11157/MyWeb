package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.mapper.UserMapper;
import com.wanting.me.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public Integer add(User user) throws Exception {


        // nn

        Integer add = userMapper.add(user);

        //

        return add;
    }


    @Override
    public Integer update(User user) throws Exception {
        Integer update = userMapper.update(user);

        return update;
    }

    @Transactional
    @Override
    public Integer del(int id) throws Exception {

        //查有没有成绩
        Integer[] ids = new Integer[1];
        ids[0]=id;
        List<Score> scores = scoreMapper.getByStuIds(ids);
        if(scores != null && scores.size()>0)

            for(Score score:scores)
                scoreMapper.del(score);
        Integer del = userMapper.del(id);

        return del;
    }

    @Transactional
    @Override
    public Integer dels(Integer[] ids) throws Exception {
        //查有没有成绩
        Integer del;
//        Integer[] stuIds = ids;
        System.out.println(ids);
        List<Score> scores = scoreMapper.getByStuIds(ids);
        System.out.println(scores);
        if(scores != null && scores.size()>0) {
            Integer a=scoreMapper.dels(scores);
            if(a==scores.size())
                del=userMapper.dels(ids);
            else{
                log.error("删除score表中studentid为："+ids+"失败");
                del =null;
            }
        }else {
            del = userMapper.dels(ids);
        }


        return del;

    }

    @Override
    public User getById(Integer id) throws Exception {
        User user = userMapper.getById(id);
        return user;
    }

    @Override
    public List<User> search(Integer page, Integer rows) throws Exception {


        Integer start = ResponsePage.initStart(page,rows);
        List<User> users = userMapper.search(start,rows);
        return users;
    }

    @Override
    public Integer countTotal() throws Exception {
//        int start = ResponsePage.initStart(page,rows);
        Integer total = userMapper.countTotal();
        return total;
    }

    @Override
    public String login(String username, String password) throws Exception {
        // 检验 username password

        List<User> users = userMapper.getByHaoMa(username);

        if(users != null && users.size()>0){
            //for(User user:users){
            User user = users.get(0);
                String pwd = user.getPassword();
                if(pwd.equals(password)){
                    return user.getName();
                }
            //}
        }
        return null;
    }
}
