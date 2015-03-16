package com.imageviewer.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Datum {

    @Expose
    private Object attribution;
    @Expose
    private List<Object> tags = new ArrayList<Object>();
    @Expose
    private Object location;
    @Expose
    private Comments comments;
    @Expose
    private String filter;
    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @Expose
    private String link;
    @Expose
    private Likes likes;
    @Expose
    private Images images;
    @Expose
    private Images videos;
    @SerializedName("users_in_photo")
    @Expose
    private List<Object> usersInPhoto = new ArrayList<Object>();
    @Expose
    private Caption caption;
    @Expose
    private String type;
    @Expose
    private String id;
    @Expose
    private User user;

    public Object getAttribution() {
        return attribution;
    }

    public void setAttribution(Object attribution) {
        this.attribution = attribution;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<Object> getUsersInPhoto() {
        return usersInPhoto;
    }

    public void setUsersInPhoto(List<Object> usersInPhoto) {
        this.usersInPhoto = usersInPhoto;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Images getVideos() {
        return videos;
    }

    public void setVideos(Images videos) {
        this.videos = videos;
    }


}
