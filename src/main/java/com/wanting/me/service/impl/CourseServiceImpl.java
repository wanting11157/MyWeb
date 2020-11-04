package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.mapper.CourseMapper;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    @Override
    public Integer add(Course course) throws Exception{


        // nn

        Integer add = courseMapper.add(course);

        //




        return add;
    }

    @Override
    public Integer update(Course course)throws Exception {
        Integer update = courseMapper.update(course);

        return update;
    }

    @Override
    public Integer del(int id)throws Exception {

        Course course = courseMapper.getById(id);

        Integer del = courseMapper.del(id);
        // todo .....
        return del;
    }

    @Override
    public Course getById(int id) throws Exception{
        Course course = courseMapper.getById(id);
        return course;
    }

    @Override
    public List<Course> search(Course course,Integer page ,Integer rows) throws Exception{
        Integer start = ResponsePage.initStart(page,rows);
        List<Course> courses = courseMapper.search(course,start,rows);
        return courses;
    }

    @Override
    public Integer countTotal(Course course,Integer page ,Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = courseMapper.countTotal(course,start,rows);
        return total;
    }

    @Override
    public Integer dels(Integer[] ids) throws Exception {
        //查有没有成绩
        Integer del;

        List<Score> scores = scoreMapper.getByCourseIds(ids);

        if(scores != null && scores.size()>0) {
            Integer a=scoreMapper.dels(scores);
            if(a==scores.size()) {
                del=courseMapper.dels(ids);
            } else{
                log.error("删除score表中courseid为："+ids+"失败");
                del =null;
            }
        }else {
            del = courseMapper.dels(ids);
        }


        return del;

    }

    @Override
    public Map importExcel(MultipartFile file) throws Exception {
        //        1、用HSSFWorkbook打开或者创建“Excel文件对象”
        //
        //        2、用HSSFWorkbook对象返回或者创建Sheet对象
        //
        //        3、用Sheet对象返回行对象，用行对象得到Cell对象
        //
        //        4、对Cell对象读写。
        //获得文件名
        ResponseResult responseResult = new ResponseResult();
        Integer successCount = 0;
        Integer repeat =0;
        Integer error = 0;
        int add;
        Map<String,Object> map = new HashMap<>();
        List<Course> errCourse = null;
        Workbook workbook = null;
        String fileName = file.getOriginalFilename();
        if (fileName.endsWith(XLS)) {
            //2003
            workbook = new HSSFWorkbook(file.getInputStream());
        } else if (fileName.endsWith(XLSX)) {
            //2007
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            throw new Exception("文件不是Excel文件");
        }

//        Sheet sheet = workbook.getSheet("sheet1");
        Sheet sheet = workbook.getSheetAt(0);
        // 指的行数，一共有多少行+
        int rows = sheet.getLastRowNum();
        if (rows == 0) {
            throw new Exception("请填写数据");
        }
        for (int i = 1; i <= rows + 1; i++) {
            // 读取左上端单元格
            Row row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                // **读取cell**
                Course course = new Course();

                String name = getCellValue(row.getCell(0));
                course.setName(name);

                String teacherName = getCellValue(row.getCell(1));
                course.setTeacherName(teacherName);

                String college = getCellValue(row.getCell(2));
                course.setCollege(college);

                String team = getCellValue(row.getCell(3));
                course.setTeam(team);

                String remark = getCellValue(row.getCell(4));
                course.setRemark(remark);

                List<Course> resuCourse = search(course,null,2);
                if (resuCourse.size()>0){
                    repeat++;
                }else{
                    add = add(course);
                    if (add == 1) {
                        successCount++;
                    }else{
                        error++;
                        errCourse.add(course);
                        map.put("code",WebResponse.ERROR);
                    }
                }


            }
        }
        map.put("success",successCount);
        map.put("repeat",repeat);
        map.put("error",error);
        map.put("errCourse",errCourse);

        return map;
    }

    @Override
    public List<Course> searchAll() throws Exception {
        return courseMapper.searchAll();
    }

    private String getCellValue(Cell cell) {
        String value = null;
        if (cell != null) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                // 数字
                case HSSFCell.CELL_TYPE_NUMERIC:
                    value = cell.getNumericCellValue() + "";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        } else {
                            value = "";
                        }
                    } else {
                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                    value.trim();
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    value = cell.getStringCellValue();
                    value.trim();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    value = cell.getBooleanCellValue() + "";
                    value.trim();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    value = cell.getCellFormula() + "";
                    value.trim();
                    break;
//                case HSSFCell.CELL_TYPE_BLANK: // 空值
//                    value = null;
//                    break;
//                case HSSFCell.CELL_TYPE_ERROR: // 故障
//                    value = "非法字符";
//                    break;
                default:
                    value = null;
                    break;
            }
        }
        return value;
    }
}
