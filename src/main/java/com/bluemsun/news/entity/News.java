package com.bluemsun.news.entity;

import java.io.Serializable;

/**
 * Created by mafx on 2019/5/15.
 */
public class News implements Serializable {
    private int id;
    private String title;
    private int type;
    private String text;
    private String time;
    private int authorId;
    private int viewsNum;

    public int getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(int viewsNum) {
        this.viewsNum = viewsNum;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
