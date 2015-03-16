package com.imageviewer.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_ {

    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @Expose
    private String text;
    @Expose
    private From from;
    @Expose
    private String id;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
