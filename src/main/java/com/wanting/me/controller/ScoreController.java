package com.wanting.me.controller;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Score;
import com.wanting.me.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


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
    public ResponseResult add(Score score)throws Exception{
        ResponseResult result = new ResponseResult();

            Integer add = scoreService.add(score);
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

    @RequestMapping("/getById")
    @ResponseBody
    public ResponseResult getById(int id) throws Exception{
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

    @RequestMapping("/search")
    @ResponseBody
    public ResponsePage search(Score score,int page ,int rows) throws Exception{
        ResponsePage result = new ResponsePage();
        List<Score> scores = scoreService.search(score, page, rows);
        if(scores == null || scores.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {
            result.setData(scores);
            result.setPage(page);
            result.setRows(rows);

            Integer total = scoreService.countTotal(score,page,rows);

            result.setTotal(total);
        }
        return result;

    }
    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id) throws Exception{

        ResponseResult result = new ResponseResult();
        Integer del = scoreService.del(id);
        if(del == null|| del < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("删除成功");
        }
        return result;
    }

}
