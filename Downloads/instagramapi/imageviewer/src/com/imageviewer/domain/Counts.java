package com.imageviewer.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts {

    @Expose
    private Integer follows;
    @Expose
    private Integer media;
    @SerializedName("followed_by")
    @Expose
    private Integer followedBy;

    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    public Integer getMedia() {
        return media;
    }

    public void setMedia(Integer media) {
        this.media = media;
    }

    public Integer getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(Integer followedBy) {
        this.followedBy = followedBy;
    }

}
