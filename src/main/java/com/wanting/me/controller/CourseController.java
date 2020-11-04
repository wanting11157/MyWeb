package com.wanting.me.controller;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;
import com.wanting.me.service.CourseService;

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

@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

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

    /**
     * 导入
     * @param file
     * @return
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public ResponseResult importExcel(MultipartFile file) throws Exception {

        ResponseResult responseResult = new ResponseResult();

        Map resuMap = courseService.importExcel(file);


        if (resuMap.containsKey("code") == true) {
            if (WebResponse.ERROR == (int) resuMap.get("code")) {
                responseResult.setCode(WebResponse.ERROR);
            }
        }
        responseResult.setData("成功插入" + resuMap.get("success") + "条，因重复而未插入" +
                resuMap.get("repeat") + "条，插入异常" + resuMap.get("error") +
                "条，插入异常而并未插入的数据为" + resuMap.get("errCourse"));
        return responseResult;
    }

    /**
     * 导出
     * @param user 带着查询条件用户对象
     * @param response 响应对象
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(User user, HttpServletResponse response) throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("course表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("课程名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("教师名");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("院系");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("学期");
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

        List<Course> courses = courseService.searchAll();

        for (int i = 0; i < courses.size(); i++) {
            row = sheet.createRow(i + 1);
            Course course1 = courses.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell(0).setCellValue(course1.getId());
            row.createCell(1).setCellValue(course1.getName());
            row.createCell(2).setCellValue(course1.getTeacherName());
            row.createCell(3).setCellValue(course1.getCollege());
            row.createCell(4).setCellValue(course1.getTeam());
            row.createCell(5).setCellValue(course1.getCreateTime());
            row.createCell(6).setCellValue(course1.getUpdateTime());
            row.createCell(7).setCellValue(course1.getRemark());
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


}


