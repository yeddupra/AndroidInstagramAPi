package com.imageviewer.domain;

import com.google.gson.annotations.Expose;

public class ProfileDetails {

    @Expose
    private Data data;
    @Expose
    private Meta meta;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
