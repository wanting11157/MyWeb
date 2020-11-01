package com.wanting.me.entity;


public class User extends Base{

      private Integer id;
      private String name;
      private String major;
      private String haoma;
      private Integer sex;
      private String college;
      private String password;
      private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHaoma() {
        return haoma;
    }

    public void setHaoma(String haoma) {
        this.haoma = haoma;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", haoma='" + haoma + '\'' +
                ", sex=" + sex +
                ", college='" + college + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
