package com.wanting.me.dto;

import com.wanting.me.entity.Course;
import com.wanting.me.entity.Score;
import lombok.Data;

import java.io.Serializable;

/**
 * 成绩的扩展对象,增加了成绩实体没有但业务中需要用到的 课程名，学生名，老师名
 */
@Data
public class ScoreDto extends Score implements Serializable {
    private String courseName;
    private String studentName;
    private String teacherName;
}