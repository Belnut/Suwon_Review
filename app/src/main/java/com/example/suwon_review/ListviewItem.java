package com.example.suwon_review;
/**
 * Created by 연일 on 2017-05-15.
 */

public class ListviewItem {
    private String titleStr;
    private String descStr;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}
