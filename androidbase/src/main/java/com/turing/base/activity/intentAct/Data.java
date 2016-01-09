package com.turing.base.activity.intentAct;

import java.io.Serializable;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-01-09  22:34.
 * @version 1.0
 * @desc
 */
public class Data implements Serializable {

    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}