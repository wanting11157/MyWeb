package com.wanting.me.entity;

public class Score extends Base{

    private int id;
    private int courseId;
    private int score;
    private int studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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
