package com.imageviewer.domain;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {

    @SerializedName("access_token")
    private String accessToken;
    private User user;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
