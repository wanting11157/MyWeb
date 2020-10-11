package com.wanting.me.controller;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Course;
import com.wanting.me.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private CourseService courseService;


//    @RequestMapping("/login")
//    @ResponseBody
//    public ResponseResult login(int stuid,String stupassword){
//        ResponseResult res = new ResponseResult();
        //Map result =  studentService.login(stuid,stupassword);
        //res.setData(result);
//        return res;
//    }


    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(Course course){
        ResponseResult result = new ResponseResult();
        Integer add = courseService.add(course);
        if(add == null || add < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("注册成功");
        }
        return result;

    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(Course course){
        ResponseResult result = new ResponseResult();
        Integer add = courseService.update(course);
        if(add == null || add < 1){
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
        Course course = courseService.getById(id);
        if(course == null){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else {
            result.setData(course);
        }
        return result;

    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponsePage search(Course course,int page ,int rows){
        ResponsePage result = new ResponsePage();
        List<Course> courses = courseService.search(course, page, rows);
        if(courses == null || courses.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {
            result.setData(courses);
            result.setPage(page);
            result.setRows(rows);

            Integer total = courseService.countTotal(course,page,rows);

            result.setTotal(total);
        }
        return result;

    }
    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id){

        ResponseResult result = new ResponseResult();
        Integer del = courseService.del(id);
        if(del == null|| del < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("删除成功");
        }
        return result;
    }

}