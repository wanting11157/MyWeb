package com.wanting.me.controller;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.User;

import com.wanting.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
   /* @Autowired
    private UserMapper userMapper;*/

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(int id,String password){
        ResponseResult res = new ResponseResult();
        //Map result =  studentService.login(stuid,stupassword);
        //res.setData(result);
        return res;
    }


    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(User user){
        ResponseResult result = new ResponseResult();
        Integer add = userService.add(user);
        if(add == null || add.intValue() < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("注册成功");
        }
        return result;

    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(User user){
        ResponseResult result = new ResponseResult();
        Integer add = userService.update(user);
        if(add == null || add.intValue() < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("修改成功");
        }
        return result;

    }

    @RequestMapping("/getById")
    @ResponseBody
    public ResponseResult getById(int id){
        ResponseResult result = new ResponseResult();
        User user = userService.getById(id);
        if(user == null){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else {
            result.setData(user);
        }
        return result;

    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponsePage search(User user,int page ,int rows){
        ResponsePage result = new ResponsePage();
        List<User> users = userService.search(user, page, rows);
        if(users.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {
            result.setData(users);
            result.setPage(page);
            result.setRows(rows);

            Integer total = userService.countTotal( user, page , rows);

            result.setTotal(total);
        }
        return result;

    }
    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id){

        ResponseResult result = new ResponseResult();
        Integer del = userService.del(id);
        if(del == null||del.intValue()<1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("删除成功");
        }
        return result;
    }

}
