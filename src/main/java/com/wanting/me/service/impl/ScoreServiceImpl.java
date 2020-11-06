package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.dto.ScoreDto;
import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;
import com.wanting.me.mapper.CourseMapper;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.mapper.UserMapper;
import com.wanting.me.service.CourseService;
import com.wanting.me.service.ScoreService;
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

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {


    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseMapper courseMapper;

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    @Override
    public Integer add(Score score)throws Exception {

        Integer add;
         List<Score> scores = scoreMapper.checkrepeatscore(score);
         if(scores!=null && scores.size()>0){
             add = null;
         }else {
             add=scoreMapper.add(score);
         }
        ;

        return add;
    }

    @Override
    public Integer update(Score score)throws Exception {
        Integer update = scoreMapper.update(score);

        return update;
    }

    @Override
    public Integer save(Score score) throws Exception {

        if(score != null){
            if(score.getId() == null){
                return  this.add(score);
            }else {
                return this.update(score);
            }
        }
        return null;
    }


    @Override
    public Integer del(Score score)throws Exception {
        Integer del = scoreMapper.del(score);

        return del;
    }

    @Override
    public List<Score> getByCourseIds(Integer[] courseIds) throws Exception {
        List<Score> scores = scoreMapper.getByCourseIds(courseIds);
        return scores;
    }

    @Override
    public List<Score> getByUserAndCourse(Integer courseId, Integer studentId) throws SQLException {
        return scoreMapper.getByUserAndCourse(courseId,studentId);
    }

    @Override
    public Score getById(Integer id)throws Exception {
        Score score = scoreMapper.getById(id);
        return score;
    }

    @Override
    public List<ScoreDto> search(Score score, Integer page, Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        List<ScoreDto> scores = scoreMapper.search(score,start,rows);
        return scores;
    }

    @Override
    public Integer countTotal(Score score,Integer page, Integer rows)throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = scoreMapper.countTotal(score,start,rows);
        return total;
    }

    @Override
    public Integer getScore(Integer courseId, Integer studentId) throws Exception {
        return scoreMapper.getScore(courseId,studentId);
    }

    @Override
    public List<Score> getByStuIds(Integer[] stuIds) throws Exception {
        return scoreMapper.getByStuIds(stuIds);
    }

    @Override
    public Integer dels(Integer[] ids) throws Exception {
        return scoreMapper.delByIds(ids);
    }

    @Override
    public List<ScoreDto> searchAll() throws Exception {
        return scoreMapper.searchAll();
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
        Map<Object,Object> map = new HashMap<>();
        User user2 = new User();
        List<User> resuUser;
        Course course2 =new Course();
        List<Course> resuCourse;
        Score score2 = new Score();
        List<ScoreDto> resuScoreDto;

        List<ScoreDto> errScoreDto = null;
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

                String name = getCellValue(row.getCell(0));

                user2.setName(name);

                resuUser = userMapper.search(user2,0,2);

                if(resuUser==null||resuUser.size()==0){
                    error++;
                    map.put("表格第"+(i+1)+"行的学生姓名:"+name,"数据库没有存有这个学生的名字");
                }
                else if(resuUser.size()>1){
                    error++;
                    map.put("表格第"+(i+1)+"行的学生姓名:"+name,"数据库user表中这个学生的名字被存了两次或两次以上");
                }
                else{
                    String courseName = getCellValue(row.getCell(2));
                    course2.setName(courseName);
                    String teacherName = getCellValue(row.getCell(3));
                    course2.setTeacherName(teacherName);
                    resuCourse = courseMapper.search(course2,0,2);
                    if(resuCourse==null||resuCourse.size()==0){
                        error++;
                        map.put("表格第"+(i+1)+"行的课程名字和教师名字:"+courseName+","+teacherName,"数据库没有存有这个课程名字和教师名字对应的课程");
                    }
                    else if(resuCourse.size()>1){
                        error++;
                        map.put("表格第"+(i+1)+"行的课程名字和教师名字:"+courseName+","+teacherName,"数据库course表这个课程名字和教师名字对应的课程有两个或两个以上");
                    }
                    else{
                        score2.setStudentId(resuUser.get(0).getId());
                        score2.setCourseId(resuCourse.get(0).getId());
                        Integer score3 = Integer.parseInt(getCellValue(row.getCell(1)));
                        score2.setScore(score3);
                        String remark = getCellValue(row.getCell(4));
                        score2.setRemark(remark);
                        resuScoreDto = scoreMapper.search(score2,0,1);
                        if(resuScoreDto.size()==1){
                            repeat++;
                        }
                        else{
                            Integer add1 = scoreMapper.add(score2);
                            if(add1==1){
                                successCount++;
                            }
                            else{
                                error++;
                                map.put("表格第"+(i+1)+"行的学生姓名:"+name+",成绩:"+score3+",课程名字:"+courseName+
                                        ",教师名字："+teacherName+",备注:"+remark,"添加到数据库失败");
                            }

                        }

                    }

                }



            }
        }
        map.put("success",successCount);
        map.put("repeat",repeat);
        map.put("error",error);

        return map;
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
