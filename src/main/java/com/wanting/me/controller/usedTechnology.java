package com.wanting.me.controller;

import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.service.TechService;
import com.wanting.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tech")
public class usedTechnology {

    @Autowired
    private TechService techService;

    @RequestMapping("/save")
    @ResponseBody
    public ResponseResult save(String content) throws Exception{
        ResponseResult responseResult = new ResponseResult();

        Integer save = techService.save(content);
        if(save!=1){
            responseResult.setCode(WebResponse.ERROR);
        }
        return responseResult;

    }

    @RequestMapping("/show")
    @ResponseBody
    public ResponseResult show() throws Exception{
        ResponseResult responseResult = new ResponseResult();

        String content = techService.show();
        if(content==null||content==""){
            responseResult.setCode(WebResponse.NODATA);
        }else{
            responseResult.setData(content);
        }
        return responseResult;

    }

}
