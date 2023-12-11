package com.example.englishapp.Model;

import android.graphics.Bitmap;

public class Topic {
    private Bitmap img;
    private String titleTopic;
    private String status;
    private String process;

    public Topic(Bitmap img, String titleTopic, String status, String process) {
        this.img = img;
        this.titleTopic = titleTopic;
        this.status = status;
        this.process = process;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getProcess() {
        return process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getTitleTopic() {
        return titleTopic;
    }

    public void setTitleTopic(String titleTopic) {
        this.titleTopic = titleTopic;
    }
}
