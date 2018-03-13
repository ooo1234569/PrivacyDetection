package com.example.bingnanfeng02.privacydetection.data;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by bingnanfeng02 on 2017/10/27.
 */

public class Pyq  {
    private String name;
    private String text;
    private int permission;
    private int id;
    private String touxianglujing;
    private String tuxianglujing;
    private Date date;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTouxianglujing() {
        return touxianglujing;
    }

    public void setTouxianglujing(String touxianglujing) {
        this.touxianglujing = touxianglujing;
    }

    public String getTuxianglujing() {
        return tuxianglujing;
    }

    public void setTuxianglujing(String tuxianglujing) {
        this.tuxianglujing = tuxianglujing;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
