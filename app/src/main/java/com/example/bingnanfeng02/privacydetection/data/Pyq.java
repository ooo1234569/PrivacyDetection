package com.example.bingnanfeng02.privacydetection.data;

import org.litepal.crud.DataSupport;

/**
 * Created by bingnanfeng02 on 2017/10/27.
 */

public class Pyq  {
    private String name;
    private String text;
    private int permission;
    private int id;
    private boolean istest=false;

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

    public boolean istest() {
        return istest;
    }

    public void setIstest(boolean istest) {
        this.istest = istest;
    }
}
