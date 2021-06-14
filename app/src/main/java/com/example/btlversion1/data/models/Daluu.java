package com.example.btlversion1.data.models;

import java.io.Serializable;

public class Daluu implements Serializable {
    public int id;
    public String title;
    public String img;
    public String link;
    public  String time;

    public Daluu() {
    }

    public Daluu(int id, String title, String img, String link, String time) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.link = link;
        this.time = time;
    }

    public Daluu(String title, String img, String link, String time) {
        this.title = title;
        this.img = img;
        this.link = link;
        this.time = time;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

