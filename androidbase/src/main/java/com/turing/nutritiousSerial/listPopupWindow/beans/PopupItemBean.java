package com.turing.nutritiousSerial.listPopupWindow.beans;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-25  11:30.
 * @version 1.0
 * @desc
 */
public class PopupItemBean {

    private String text;


    public PopupItemBean(String text) {
        this.text = text;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
