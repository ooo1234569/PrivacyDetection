package com.example.bingnanfeng02.privacydetection.data;

import java.util.Date;
import java.util.List;

/**
 * Created by bingnanfeng02 on 2018/3/12.
 */

public class SendPyqReturn {
    private String detected;
    private int id;
    private String privacy;
    private String text;
    private List<String> stringList;
    private String content;
    private Date published_time;
    private int level;

    public String getDetected() {
        return detected;
    }

    public void setDetected(String detected) {
        this.detected = detected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublished_time() {
        return published_time;
    }

    public void setPublished_time(Date published_time) {
        this.published_time = published_time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
