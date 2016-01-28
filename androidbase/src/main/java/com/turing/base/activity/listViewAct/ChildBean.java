package com.turing.base.activity.listViewAct;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-01-28  14:55.
 * @version 1.0
 *  两个构造函数  属性的set + get
 */
public class ChildBean {

    private String name;
    private String sign;


    public ChildBean(String name, String sign) {
        this.name = name;
        this.sign = sign;
    }


    public ChildBean() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
