package com.wanting.me.entity;

import java.io.Serializable;
import java.util.List;

public class Score extends Base implements Serializable {

    private Integer id;
    private Integer courseId;
    private Integer score;
    private Integer studentId;

    List<Course> courseList;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", score=" + score +
                ", studentId=" + studentId +
                '}';
    }
}
