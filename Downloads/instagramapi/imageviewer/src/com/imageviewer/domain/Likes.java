package com.imageviewer.domain;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Likes {

    @Expose
    private Integer count;
    @Expose
    private List<Datum__> data = new ArrayList<Datum__>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }

}
