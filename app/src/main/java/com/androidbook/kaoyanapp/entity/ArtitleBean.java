package com.androidbook.kaoyanapp.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ArtitleBean implements Serializable {
    private String user;

    private String tittle;

    private String text;

    private String time;

    private String weight;

    private String issue;

    private String category;

    public ArtitleBean(String user, String tittle, String text, String time, String weight, String issue, String category) {
        this.user = user;
        this.tittle = tittle;
        this.text = text;
        this.time = time;
        this.weight = weight;
        this.issue = issue;
        this.category = category;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ArtitleBean{" +
                "user='" + user + '\'' +
                ", tittle='" + tittle + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", weight='" + weight + '\'' +
                ", issue='" + issue + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
