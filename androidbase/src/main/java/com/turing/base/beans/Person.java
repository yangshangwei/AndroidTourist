package com.turing.base.beans;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-20  00:55.
 * @version 1.0
 * @desc
 */
public class Person {

    private String name ;
    private String age ;
    private String url ;

    private List<School> schoolList ;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }
}
