package com.wanting.me.controller;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Course;
import com.wanting.me.service.CourseService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(Course course) throws Exception{
        ResponseResult result = new ResponseResult();
        Integer add = courseService.add(course);
        if(add == null || add < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("增加课程成功");
        }
        return result;

    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(Course course) throws Exception{
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
    public ResponseResult getById(int id) throws Exception{
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
    public ResponsePage search(Course course,Integer page ,Integer rows) throws Exception{
        ResponsePage result = new ResponsePage();
        List<Course> courses = courseService.search(course,page,rows);
        if(courses == null || courses.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {
            result.setData(courses);
//            result.setPage(page);
//            result.setRows(rows);

            Integer total = courseService.countTotal(course,page,rows);

            result.setTotal(total);
        }
        return result;

    }
    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id) throws Exception{

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

    @RequestMapping("/dels")
    @ResponseBody
    public ResponseResult dels( @RequestParam("ids[]") Integer[] ids) throws Exception{


        ResponseResult result = new ResponseResult();
        Integer del = courseService.dels(ids);
        if(del == null || del < 1){

            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);

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
