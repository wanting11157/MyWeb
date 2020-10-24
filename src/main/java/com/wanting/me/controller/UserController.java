package com.wanting.me.controller;


import com.alibaba.fastjson.JSONObject;
import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.User;
import com.wanting.me.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    //Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(String username,String password) throws Exception{
        ResponseResult res = new ResponseResult();
        /**
         * 登录成功返回 名字
         * 失败 返看回 空 null
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
    public ResponsePage search(@RequestParam(required = false) Integer page , Integer rows) throws Exception{
        ResponsePage result = new ResponsePage();
        List<User> users = userService.search(page,rows);
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
//            result.setPage(page);
//            result.setRows(rows);

            Integer total = userService.countTotal();

            result.setTotal(total);
        }
        return result;

    }
    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id) throws Exception{

        ResponseResult result = new ResponseResult();
        Integer del = userService.del(id);
        if(del == null||del<1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("删除成功");
        }
        return result;
    }

    @RequestMapping("/dels")
    @ResponseBody
    public ResponseResult dels( @RequestParam("ids[]") Integer[] ids) throws Exception{

//        String idsJSONString = JSONObject.toJSONString(ids);
//        log.info("执行了dels。。。。。ids = " + idsJSONString);

//        boolean delSuccess = true;
//        String msg = "";
//        for(Integer id : ids) {
//            Integer del = userService.del(id);
//            if(del<1) {
//                delSuccess = false;
//                msg += "id = "+id +" 删除失败";
//            }
//        }

        ResponseResult result = new ResponseResult();
        Integer del = userService.dels(ids);
        if(del == null || del < 1){

            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
//            log.error("删除失败");
//
//            log.info("");
//
//            log.warn("");
//
//            log.error("id == null ");


            //日志级别
        }else {
            if(del != ids.length){
                log.warn("有部分id无效");
                result.setCode(WebResponse.ERROR);
                result.setMsg("有部分id无效");
            }else {
                result.setMsg("删除成功");
            }
        }
        return result;
    }

}
