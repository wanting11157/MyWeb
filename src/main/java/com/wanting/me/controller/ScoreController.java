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
import java.util.Map;
import java.util.Set;

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

    /**
     * 导出
     *
     * @param response 响应对象
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("score表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("学生姓名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("成绩");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("课程名");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("教师名");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("更新时间");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        List<ScoreDto> scoreDtos = scoreService.searchAll();

        for (int i = 0; i < scoreDtos.size(); i++) {
            row = sheet.createRow(i + 1);
            ScoreDto scoreDto1 = scoreDtos.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell(0).setCellValue(scoreDto1.getId());
            row.createCell(1).setCellValue(scoreDto1.getStudentName());
            row.createCell(2).setCellValue(scoreDto1.getScore());
            row.createCell(3).setCellValue(scoreDto1.getCourseName());
            row.createCell(4).setCellValue(scoreDto1.getTeacherName());
            row.createCell(5).setCellValue(scoreDto1.getCreateTime());
            row.createCell(6).setCellValue(scoreDto1.getUpdateTime());
            row.createCell(7).setCellValue(scoreDto1.getRemark());
        }
        //第六步,输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
//        long filename = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String fileName = df.format(new Date());// new Date()为获取当前系统时间
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }

    /**
     * 导入
     * @param file
     * @return
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public ResponseResult importExcel(MultipartFile file) throws Exception {

        ResponseResult responseResult = new ResponseResult();

        Map resuMap = scoreService.importExcel(file);

        Integer successCount = (int)resuMap.get("success");
        Integer repeat = (int)resuMap.get("repeat");
        Integer error = (int)resuMap.get("error");
        resuMap.remove("success");
        resuMap.remove("repeat");
        resuMap.remove("error");
        String data = "成功插入" + successCount + "条，因重复而未插入" +
                repeat + "条，插入异常" + error + "条";

        if(error>0){
            responseResult.setCode(WebResponse.ERROR);
            Set<Map.Entry<Object,Object>> entries = resuMap.entrySet();
            data = data +"。错误信息为：";
            for(Map.Entry<Object,Object> entry:entries){
                data = data + entry.getKey()+"存在问题，问题是："+entry.getValue();
            }
        }

        responseResult.setData(data);
        return responseResult;
    }
}
