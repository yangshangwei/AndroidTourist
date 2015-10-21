package com.turing.base.beans;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-22  01:04.
 * @version 1.0
 * @desc
 */
public class ClassBean {

    private String id;
    private String name;
    private List<StudentBean> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentBean> getList() {
        return list;
    }

    public void setList(List<StudentBean> list) {
        this.list = list;
    }

}
