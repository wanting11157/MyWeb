package com.wanting.me.service;


import com.wanting.me.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface UserService {
    Integer add(User user)throws Exception;
    Integer update(User user)throws Exception;
    Integer del(int id)throws Exception;
    Integer dels(Integer[] ids)throws Exception;
    User getById(Integer id)throws Exception;
    List<User> search(User user,Integer page, Integer rows)throws Exception;
    Integer countTotal(User user,Integer page, Integer rows)throws Exception;

    String login(String username, String password)throws Exception;

    void hasPhoto(Integer id)throws Exception;



    List<User> searchAll() throws Exception;

    Map importExcel(MultipartFile file) throws Exception;
}