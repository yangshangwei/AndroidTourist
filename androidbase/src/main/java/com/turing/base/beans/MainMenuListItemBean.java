package com.turing.base.beans;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/10  15:38.
 * @version 1.0
 * @desc
 */
public class MainMenuListItemBean {
    // ListView展示的图片ID
    private int imageId;
    // ListView展示的文字内容
    private String content ;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
