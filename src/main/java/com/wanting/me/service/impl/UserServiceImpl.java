package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.entity.User;
import com.wanting.me.mapper.UserMapper;
import com.wanting.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


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

    @Override
    public Integer del(int id) throws Exception {
        Integer del = userMapper.del(id);
        // todo .....
        return del;
    }

    @Override
    public User getById(Integer id) throws Exception {
        User user = userMapper.getById(id);
        return user;
    }

    @Override
    public List<User> search(User user, Integer page, Integer rows) throws Exception {


        int start = ResponsePage.initStart(page,rows);
        List<User> users = userMapper.search(user, start, rows);
        return users;
    }

    @Override
    public Integer countTotal(User user, Integer page, Integer rows) throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = userMapper.countTotal(user, start, rows);
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
