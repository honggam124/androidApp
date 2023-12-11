package com.example.englishapp.Model;

public class Vocab {
    private String en;
    private String vi;
    private String topic;
    public Vocab(){

    }
    public Vocab(String en,String vi,String topic){
        this.en=en;
        this.vi=vi;
        this.topic=topic;
    }
    public String getEn() {
        return en;
    }

    public String getVi() {
        return vi;
    }

    public String getTopic() {
        return topic;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
