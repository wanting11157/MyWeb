package com.wanting.me.controller;


import com.wanting.me.common.ResponsePage;
import com.wanting.me.common.ResponseResult;
import com.wanting.me.common.WebResponse;
import com.wanting.me.entity.User;
import com.wanting.me.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    //Logger log = LoggerFactory.getLogger(UserController.class);
    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    @Autowired
    private UserService userService;
    @Value("${upload.temp}")
    private String basePath;

    @Value("${upload.user.img}")
    private String userImg;
    //private UserServiceImpl userService1 = new UserServiceImpl2() ;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(String username,String password) throws Exception{
        ResponseResult res = new ResponseResult();
        /**
         * 登录成功返回 名字
         * 失败 返看回 空 null
         */
        String name = userService.login(username,password);
        if( name == null ){
            res.setCode(WebResponse.ERROR);
            res.setMsg(WebResponse.MSG_ERROR);
        }else {
            res.setData(name);
        }
        return res;
    }


    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(User user ) throws Exception{

        ResponseResult result = new ResponseResult();
        Integer add = userService.add(user);
        if(add == null || add.intValue() < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("注册成功");
//            Integer page=null;
//            Integer rows=30;
//            List<User>  users = userService.search(user,page,rows);
//            log.info(users.toString());
//            User resuUser = users.get(0);
//            result.setData(resuUser.getId());
        }
        return result;

    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(User user) throws Exception{
        ResponseResult result = new ResponseResult();
        Integer add = userService.update(user);
        if(add == null || add.intValue() < 1){
            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
        }else {
            result.setMsg("修改成功");
        }
        return result;

    }

    @RequestMapping("/getById")
    @ResponseBody
    public ResponseResult getById(Integer id) throws Exception{
        ResponseResult result = new ResponseResult();
        User user = userService.getById(id);
        if(user == null){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else {
            result.setData(user);
        }
        return result;

    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponsePage search(User user, @RequestParam(required = false) Integer page , Integer rows) throws Exception{
        ResponsePage result = new ResponsePage();
        List<User> users = userService.search(user,page,rows);
        if(users.size() < 1){
            result.setCode(WebResponse.NODATA);
            result.setMsg(WebResponse.MSG_NODATA);
        }else   {

//            User $= new User();
//
//            $.getName();
//            //$(".fjaewf")
//            $.setName("zhc");
//
//            $.getName();


            result.setData(users);
//            result.setPage(page);
//            result.setRows(rows);

            Integer total = userService.countTotal(user,page,rows);

            result.setTotal(total);
        }
        return result;

        // postHandle
        //---spring 对响应信息的封装处理
        // afterHandle   把对象序列化成json字符串


    }
    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id) throws Exception{

        ResponseResult result = new ResponseResult();
        Integer del = userService.del(id);
        if(del == null||del<1){
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

//        String idsJSONString = JSONObject.toJSONString(ids);
//        log.info("执行了dels。。。。。ids = " + idsJSONString);

//        boolean delSuccess = true;
//        String msg = "";
//        for(Integer id : ids) {
//            Integer del = userService.del(id);
//            if(del<1) {
//                delSuccess = false;
//                msg += "id = "+id +" 删除失败";
//            }
//        }

        ResponseResult result = new ResponseResult();
        Integer del = userService.dels(ids);
        if(del == null || del < 1){

            result.setCode(WebResponse.ERROR);
            result.setMsg(WebResponse.MSG_ERROR);
//            log.error("删除失败");
//
//            log.info("");
//
//            log.warn("");
//
//            log.error("id == null ");


            //日志级别
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

    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResponseResult uploadFile(MultipartFile file) throws Exception{

        ResponseResult result = new ResponseResult();
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf('.');
        String endName = originalFilename.substring(index);
        log.info("userImg= "+userImg + ",文件名："+ originalFilename);
        File userPath = new File(basePath+userImg);
        boolean exists = userPath.exists();

        long l = System.currentTimeMillis();
        double random = Math.random();
        String s = random + "";

        if(!exists){
            userPath.mkdirs();
        }
        String path = basePath+userImg+l+s.substring(2)+endName;
        File dest = new File(path);
        try {

            file.transferTo(dest);

            result.setData(""+l+s.substring(2)+endName);
//            userService.hasPhoto(id);

        }catch (Exception e){
            String msg = "上传异常";
            result.setCode(WebResponse.ERROR);
            result.setMsg(msg);
            log.error(msg,e);
        }
        return result;
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(Integer userId, String fileName, HttpServletResponse response) {
        // 导入 导出 excel 参与 ： https://www.cnblogs.com/linjiqin/p/10975761.html

        //参考：https://www.cnblogs.com/xiaoyue1606bj/p/10985764.html

        try {
            //文件读到内存
            File rs = new File(basePath + userImg + fileName);
            FileInputStream fileInputStream = new FileInputStream(rs);
            byte[] content = new byte[fileInputStream.available()];

            fileInputStream.read(content);


            //文件从内存写到浏览器指定的地址
            response.setContentType("bin");
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            ServletOutputStream outputStream = response.getOutputStream();

            outputStream.write(content);

            //如果文件很大，要用多次读入和写出来实现
//        byte[] content = new byte[1024*1024];//一次读 1M
//        while (fileInputStream.read(content)>0){
//            outputStream.write(content);
//        }
//        outputStream.flush();

            //关闭 读入 写出 通道
            outputStream.close();
            fileInputStream.close();
        }catch (IOException e){
            log.error("下载IO异常:"+e.getMessage());
        }
    }

    /**
     * 导入
     * @param file
     * @return
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public ResponseResult importExcel(MultipartFile file) throws Exception {

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

        Sheet sheet = workbook.getSheet("sheet1");
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

                List<User> resuUser = userService.search(user,null,2);
                if (resuUser.size()>0){
                    repeat++;
                }else{
                    add = userService.add(user);
                    if (add == 1) {
                        successCount++;
                    }else{
                        error++;
                        errUser.add(user);
                        responseResult.setCode(WebResponse.ERROR);
                    }
                }


            }
        }
        responseResult.setData("成功插入"+successCount+"条，因重复而未插入"+repeat+"条，插入异常"
                +error+"条，插入异常而并未插入的数据为"+errUser);
        return responseResult;
    }

    public String getCellValue(Cell cell) {
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

        /**
         * 导出
         * @param user 带着查询条件用户对象
         * @param response 响应对象
         */
    @RequestMapping("/exportFile")
    public void exportFile(User user, HttpServletResponse response) throws Exception {
        // 导入 导出 excel 参与 ： https://www.cnblogs.com/linjiqin/p/10975761.html
//        try {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("专业");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("学号");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("性别");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("院系");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("密码");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("更新时间");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("头像");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
//                List<Student> list = userMapper.selectAll();
        //查出要导出的用户信息 List<User> users;
        List<User> users = userService.searchAll();

        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 1);
            User user1 = users.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell(0).setCellValue(user1.getId());
            row.createCell(1).setCellValue(user1.getName());
            row.createCell(2).setCellValue(user1.getMajor());
            row.createCell(3).setCellValue(user1.getHaoma());
            if (user1.getSex() == 1) {
                row.createCell(4).setCellValue("男");
            } else if (user1.getSex() == 2) {
                row.createCell(4).setCellValue("女");
            }
            row.createCell(5).setCellValue(user1.getCollege());
            row.createCell(6).setCellValue(user1.getPassword());
            row.createCell(7).setCellValue(user1.getUpdateTime());
            row.createCell(8).setCellValue(user1.getCreateTime());
            row.createCell(9).setCellValue(user1.getRemark());
            row.createCell(10).setCellValue(user1.getPhoto());
//                    cell = row.createCell(3);
//                    cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(stu.getTime()));
        }
        //第六步,输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        long filename = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String fileName = df.format(new Date());// new Date()为获取当前系统时间
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }
//            }
//
//
//            //通过POI把 users  转成 excel文件
//            //  POI  api
//            //把excel文件通过文件流的形式下载
//            //File file = from users to Excel File;
//            //TODO 对象集合 转成 Excel文件   重点
//            File file = null;
//            //自己给要下载的文件命名
//            String fileName = "";
//
//            FileInputStream fileInputStream = new FileInputStream(file);
//
//            byte[] content = new byte[fileInputStream.available()];
//
//            fileInputStream.read(content);
//
//
//            //文件从内存写到浏览器指定的地址
//            response.setContentType("bin");
//            response.setHeader("Content-Disposition","attachment;filename="+fileName);
//            ServletOutputStream outputStream = response.getOutputStream();
//
//            outputStream.write(content);
//
//            //如果文件很大，要用多次读入和写出来实现
////        byte[] content = new byte[1024*1024];//一次读 1M
////        while (fileInputStream.read(content)>0){
////            outputStream.write(content);
////        }
////        outputStream.flush();
//
//            //关闭 读入 写出 通道
//            outputStream.close();
//            fileInputStream.close();
//        }catch (IOException e){
//            log.error("导出时IO异常:"+e.getMessage());
//        } catch (Exception e) {
//            //e.printStackTrace();
//            log.error("导出时查询用户信息异常："+ e.getMessage(),e);
//        }
//    }

}
