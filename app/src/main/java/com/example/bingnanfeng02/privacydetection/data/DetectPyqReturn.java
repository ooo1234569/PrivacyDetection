package com.example.bingnanfeng02.privacydetection.data;

/**
 * Created by bingnanfeng02 on 2018/3/12.
 */

public class DetectPyqReturn {
    private int detected_level;
    private String privacy;
    private String detected;
    private String content;
    private String matched_rule;
    private int matched_level;
    private int recommend_level;
    private int id;

    private String text;
    private String images;
    private String published_time;





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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public int getDetected_level() {
        return detected_level;
    }

    public void setDetected_level(int detected_level) {
        this.detected_level = detected_level;
    }

    public int getMatched_level() {
        return matched_level;
    }

    public void setMatched_level(int matched_level) {
        this.matched_level = matched_level;
    }

    public int getRecommend_level() {
        return recommend_level;
    }

    public void setRecommend_level(int recommend_level) {
        this.recommend_level = recommend_level;
    }

    public String getMatched_rule() {
        return matched_rule;
    }

    public void setMatched_rule(String matched_rule) {
        this.matched_rule = matched_rule;
    }
}
