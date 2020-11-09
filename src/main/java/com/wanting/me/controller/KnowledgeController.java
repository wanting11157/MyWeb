package com.wanting.me.controller;

import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Knowledge;
import com.wanting.me.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/know")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping("/start")
    @ResponseBody
    public ResponseResult start() throws Exception{
        ResponseResult responseResult = new ResponseResult();

        List<Knowledge> knowledges = knowledgeService.start();
        if(knowledges==null||knowledges.size()==0){
            responseResult.setCode(WebResponse.NODATA);
        }else {
            responseResult.setData(knowledges);
        }
        return responseResult;

    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(Integer parentId) throws Exception{
        ResponseResult responseResult = new ResponseResult();

        Integer add1 = knowledgeService.add(parentId);
        if(add1!=1){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg(WebResponse.MSG_ERROR);
        }
        return responseResult;
    }

    @RequestMapping("/submit")
    @ResponseBody
    public ResponseResult submit(Knowledge knowledge) throws Exception{
        ResponseResult responseResult = new ResponseResult();

        Integer submit = knowledgeService.submit(knowledge);
        if(submit!=1){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg(WebResponse.MSG_ERROR);
        }
        return responseResult;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(Knowledge knowledge) throws Exception{
        ResponseResult responseResult = new ResponseResult();

        Integer update = knowledgeService.update(knowledge);
        if(update!=1){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg(WebResponse.MSG_ERROR);
        }
        return responseResult;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id) throws Exception{
        ResponseResult responseResult = new ResponseResult();

        Integer del = knowledgeService.del(id);
        if(del<1){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg(WebResponse.MSG_ERROR);
        }
        return responseResult;
    }


}
