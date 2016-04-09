package com.turing.base.activity.webview.WebView_Js_inter;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-24  15:24.
 * @version 1.0
 * @desc
 */
public class Contact {

    private String id;
    private String name;
    private String phone;

    public Contact() {
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.id + "~" + this.name + "~" + this.phone;
    }
}
