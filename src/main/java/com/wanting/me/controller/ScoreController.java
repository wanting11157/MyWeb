package com.wanting.me.controller;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.dto.ScoreDto;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;
import com.wanting.me.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";


    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(Score score)throws Exception{
        ResponseResult result = new ResponseResult();

            Integer add = scoreService.add(score);
            if(add == null || add < 1){
                result.setCode(WebResponse.ERROR);
                result.setMsg(WebResponse.MSG_ERROR);
            }else {
                result.setMsg("成绩加入成功");
            }


        return result;

    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(Score score)throws Exception{
        ResponseResult result = new ResponseResult();
        Integer add = scoreService.update(score);
        if(add == null || add < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("修改成功");
        }
        return result;

    }

    @RequestMapping("/save")
    @ResponseBody
    public ResponseResult save(Score score)throws Exception{
        ResponseResult result = new ResponseResult();
        Integer save = scoreService.save(score);
        if(save == null || save < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("保存成功");
        }
        return result;

    }


    @RequestMapping("/getById")
    @ResponseBody
    public ResponseResult getById(Integer id) throws Exception{
        ResponseResult result = new ResponseResult();
        Score score = scoreService.getById(id);
        if(score == null){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else {
            result.setData(score);
        }
        return result;

    }

    @RequestMapping("/getByStuIds")
    @ResponseBody
    public ResponseResult getByStuIds(@RequestParam("stuIds[]")Integer[] stuIds) throws Exception{
        ResponseResult result = new ResponseResult();
        List<Score> scores = scoreService.getByStuIds(stuIds);
        if(scores == null||scores.size()==0){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }
        return result;

    }
    @RequestMapping("/getByCourseIds")
    @ResponseBody
    public ResponseResult getByCourseIds(@RequestParam("courseIds[]")Integer[] courseIds) throws Exception{
        ResponseResult result = new ResponseResult();
        List<Score> scores = scoreService.getByCourseIds(courseIds);
        if(scores == null || scores.size()==0){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }
        return result;

    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponsePage search(Score score,Integer page, Integer rows) throws Exception{
        ResponsePage result = new ResponsePage();
        List<ScoreDto> scores = scoreService.search(score,page,rows);
        if(scores == null || scores.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {
            result.setData(scores);
//            result.setPage(page);
//            result.setRows(rows);

            Integer total = scoreService.countTotal(score,page,rows);

            result.setTotal(total);
        }
        return result;

    }
    @RequestMapping("/getByUserAndCourse")
    @ResponseBody
    public ResponsePage getByUserAndCourse(Integer courseId, Integer studentId) throws Exception{
        ResponsePage result = new ResponsePage();
        List<Score> scores = scoreService.getByUserAndCourse(courseId,studentId);
        if(scores == null || scores.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {
            result.setData(scores);
        }
        return result;

    }

    @RequestMapping("/getScore")
    @ResponseBody
    public Integer getScore(Integer courseId, Integer studentId) throws Exception{
        Integer score = scoreService.getScore(courseId,studentId);
        return  score ==null ?0: score;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Score score) throws Exception{

        ResponseResult result = new ResponseResult();
        Integer del = scoreService.del(score);
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
        Integer del = scoreService.dels(ids);
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
