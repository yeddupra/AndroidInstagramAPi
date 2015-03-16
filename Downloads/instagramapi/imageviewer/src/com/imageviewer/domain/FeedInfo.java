package com.imageviewer.domain;

public class FeedInfo {
    private String url;
    private String videoUrl;
    private String fullName;
    private String userName;
    private Integer likes;
    private String type;

    public FeedInfo(String url, String fullName, Integer likes, String type) {
        this.url = url;
        this.fullName = fullName;
        this.likes = likes;
        this.type = type;
    }

    public FeedInfo(String url, String videoUrl, String fullName, Integer likes, String type) {
        this.url = url;
        this.videoUrl = videoUrl;
        this.fullName = fullName;
        this.likes = likes;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


}
