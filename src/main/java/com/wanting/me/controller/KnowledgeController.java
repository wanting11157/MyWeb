package com.wanting.me.controller;

import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Knowledge;
import com.wanting.me.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/know")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @Value("${upload.temp}")
    private String basePath;

    @Value("${upload.knowledge.img}")
    private String knowledgeImg;

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

        Knowledge knowledge = new Knowledge();
        knowledge.setParentId(parentId);

        Integer add1 = knowledgeService.add(knowledge);
        if(add1!=1){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg(WebResponse.MSG_ERROR);
        }else {
            responseResult.setData(knowledge.getId());
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

    @RequestMapping("/dels")
    @ResponseBody
    public ResponseResult dels(@RequestParam("ids[]") Integer[] ids) throws Exception{
        ResponseResult responseResult = new ResponseResult();

        Integer dels = knowledgeService.dels(ids);
        if(dels==null||dels==0){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg("没有一个节点是删除成功的");
        }else if(dels!=ids.length){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg("部分节点删除成功");
        }

        return responseResult;
    }

    @RequestMapping("/genAdd")
    @ResponseBody
    public ResponseResult genAdd() throws Exception{
        ResponseResult responseResult = new ResponseResult();

        Integer genAdd = knowledgeService.genAdd();
        if(genAdd!=1){
            responseResult.setCode(WebResponse.ERROR);
            responseResult.setMsg(WebResponse.MSG_ERROR);
        }
        return responseResult;
    }

    @RequestMapping("/fuwenben")
    @ResponseBody
    public ResponseResult insertPicture(MultipartFile file) throws Exception{

        ResponseResult result = new ResponseResult();
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf('.');
        String endName = originalFilename.substring(index);
        File userPath = new File(basePath+knowledgeImg);
        boolean exists = userPath.exists();

        long l = System.currentTimeMillis();
        double random = Math.random();
        String s = random + "";

        if(!exists){
            userPath.mkdirs();
        }
        String path = basePath+knowledgeImg+l+s.substring(2)+endName;
        File dest = new File(path);
        try {

            file.transferTo(dest);
            result.setCode(0);
            Map<String,String> map = new HashMap<>(1);
            map.put("src","http://localhost/api/know/downloadFile?fileName="+l+s.substring(2)+endName);
            result.setData(map);

        }catch (Exception e){
            String msg = "上传异常";
            result.setCode(WebResponse.ERROR);
            result.setMsg(msg);
            log.error(msg,e);
        }
        return result;
    }
    @RequestMapping("/downloadFile")
    public void downloadFile(String fileName, HttpServletResponse response) {
        try {
            //文件读到内存
            File rs = new File(basePath + knowledgeImg + fileName);
            FileInputStream fileInputStream = new FileInputStream(rs);
            byte[] content = new byte[fileInputStream.available()];

            fileInputStream.read(content);


            //文件从内存写到浏览器指定的地址
            response.setContentType("bin");
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            ServletOutputStream outputStream = response.getOutputStream();

            outputStream.write(content);
            outputStream.close();
            fileInputStream.close();
        }catch (IOException e){
            log.error("下载IO异常:"+e.getMessage());
        }
    }


}
