package com.wanting.me.service.impl;

import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.Score;
import com.wanting.me.entity.User;
import com.wanting.me.mapper.ScoreMapper;
import com.wanting.me.mapper.UserMapper;
import com.wanting.me.service.UserService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public Integer add(User user) throws Exception {


        // nn

        Integer add = userMapper.add(user);

        //

        return add;
    }


    @Override
    public Integer update(User user) throws Exception {
        Integer update = userMapper.update(user);

        return update;
    }

    @Transactional
    @Override
    public Integer del(int id) throws Exception {

        //查有没有成绩
        Integer[] ids = new Integer[1];
        ids[0]=id;
        List<Score> scores = scoreMapper.getByStuIds(ids);
        if(scores != null && scores.size()>0) {
            for(Score score:scores)
                scoreMapper.del(score);
        }
        Integer del = userMapper.del(id);

        return del;
    }

    @Transactional
    @Override
    public Integer dels(Integer[] ids) throws Exception {
        //查有没有成绩
        Integer del;
//        Integer[] stuIds = ids;
        System.out.println(ids);
        List<Score> scores = scoreMapper.getByStuIds(ids);
        System.out.println(scores);
        if(scores != null && scores.size()>0) {
            Integer a=scoreMapper.dels(scores);
            if(a==scores.size()) {
                del=userMapper.dels(ids);
            } else{
                log.error("删除score表中studentid为："+ids+"失败");
                del =null;
            }
        }else {
            del = userMapper.dels(ids);
        }


        return del;

    }

    @Override
    public User getById(Integer id) throws Exception {
        User user = userMapper.getById(id);
        return user;
    }

    @Override
    public List<User> search(User user,Integer page, Integer rows) throws Exception {


        Integer start = ResponsePage.initStart(page,rows);
//        if(user!=null) {
//            if (user.getSex() != 1 && user.getSex() != 2)
//                user.setSex(null);
//        }
        List<User> users = userMapper.search(user,start,rows);

        return users;
    }

    @Override
    public Integer countTotal(User user,Integer page, Integer rows) throws Exception {
        int start = ResponsePage.initStart(page,rows);
        Integer total = userMapper.countTotal(user,start,rows);
        return total;
    }

    @Override
    public String login(String username, String password) throws Exception {
        // 检验 username password

        List<User> users = userMapper.getByHaoMa(username);

        if(users != null && users.size()>0){
            //for(User user:users){
            User user = users.get(0);
                String pwd = user.getPassword();
                if(pwd.equals(password)){
                    return user.getName();
                }
            //}
        }
        return null;
    }

    @Override
    public void hasPhoto(Integer id) throws Exception {
        userMapper.hasPhoto(id);
    }



    @Override
    public List<User> searchAll() throws Exception {
        return userMapper.searchAll();
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


    @Override
    public Map importExcel(MultipartFile file) throws Exception {
        //1、用HSSFWorkbook打开或者创建“Excel文件对象”
        //2、用HSSFWorkbook对象返回或者创建Sheet对象
        //3、用Sheet对象返回行对象，用行对象得到Cell对象
        //4、对Cell对象读写。

        Integer successCount = 0;
        Integer repeat =0;
        Integer error = 0;
        int add;
        Map<String,Object> map = new HashMap<>();
        List<User> errUser = null;
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


        /*
        这个是为了适应excel文件有多页面，就是多个sheet页
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while(sheetIterator.hasNext()){
            Sheet sheet = sheetIterator.next();

        }*/


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

            //  function covertRow2Obj(row,User)  //  返回就是user
            // 行不为空
            if (row != null) {
                // **读取cell**
                User user = new User();
                //姓名
                String name = getCellValue(row.getCell(0));
                user.setName(name);
                //班级
                String major = getCellValue(row.getCell(1));
                user.setMajor(major);
                //分数
                String haoma = getCellValue(row.getCell(2));
                user.setHaoma(haoma);

                String sex = getCellValue(row.getCell(3));
                if (sex.equals("男")) {
                    user.setSex(1);
                } else if (sex.equals("女")) {
                    user.setSex(2);
                }

                String college = getCellValue(row.getCell(4));
                user.setCollege(college);

                String password = getCellValue(row.getCell(5));
                user.setPassword(password);

                String remark = getCellValue(row.getCell(6));
                user.setRemark(remark);

                String photo = getCellValue(row.getCell(7));
                user.setPhoto(photo);

                String details = getCellValue(row.getCell(8));
                user.setDetails(details);

                List<User> resuUser = search(user,null,2);
                if (resuUser.size()>0){
                    repeat++;
                }else{
                    add = userMapper.add(user);
                    if (add == 1) {
                        successCount++;
                    }else{
                        error++;
                        errUser.add(user);
                        map.put("code",WebResponse.ERROR);
//                        responseResult.setCode();
                    }
                }


            }
        }
        map.put("success",successCount);
        map.put("repeat",repeat);
        map.put("error",error);
        map.put("errUser",errUser);

        return map;
    }


}
