package com.imageviewer.domain;

import com.google.gson.annotations.Expose;

public class Meta {

    @Expose
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
