package com.turing.base.beans;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-19  23:37.
 * @version 1.0
 * @desc
 */
public class ShowJsonBean {

    // ListView展示的图片ID
    private int imageId;
    // ListView展示的文字内容
    private String name;
    private String age;
    private String school1;
    private String school2;


    public String getSchool1() {
        return school1;
    }

    public void setSchool1(String school1) {
        this.school1 = school1;
    }

    public String getSchool2() {
        return school2;
    }

    public void setSchool2(String school2) {
        this.school2 = school2;
    }


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
