package com.wanting.me.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.User;

import com.wanting.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseResult login(String username,String password) throws Exception{
        ResponseResult res = new ResponseResult();
        /**
         * 登录成功返回 名字
         * 失败 返回 空 null
         */
        String name = userService.login(username,password);
        if( name == null ){
            res.setCode(WebResponse.ERROR);
            res.setMsg(WebResponse.MSG_ERROR);
        }else {
            res.setData(name);
        }
        return res;
    }


    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(User user ) throws Exception{

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
    public ResponseResult update(User user) throws Exception{
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
    public ResponseResult getById(Integer id) throws Exception{
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
    public ResponsePage search(User user,int page ,int rows) throws Exception{
        ResponsePage result = new ResponsePage();
        List<User> users = userService.search(user);
        if(users.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {

//            User $= new User();
//
//            $.getName();
//            //$(".fjaewf")
//            $.setName("zhc");
//
//            $.getName();


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
    public ResponseResult del(Integer id) throws Exception{

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
