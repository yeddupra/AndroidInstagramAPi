package com.imageviewer.domain;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class PopularMedia {

    @Expose
    private Meta meta;
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
