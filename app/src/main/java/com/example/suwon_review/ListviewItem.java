package com.example.suwon_review;

import android.graphics.drawable.Drawable;

/**
 * Created by K on 2017-05-15.
 */

public class ListViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String scoreStr;
    private String descStr;

    public void setIcon(Drawable icon) {
        iconDrawable = icon;
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public void setScore(String score) { scoreStr = score; }

    public void setDesc(String desc) {
        descStr = desc;
    }

    public Drawable getIcon() {
        return this.iconDrawable;
    }

    public String getTitle() {
        return this.titleStr;
    }

    public String getScore() { return this.scoreStr; }

    public String getDesc() {
        return this.descStr;
    }
}



