package com.integrate.mingweidev.mvp.bean;

/**
 * Created by 梁明伟 on 2018/11/8.
 * Copyright © 2018年 CETC. All rights reserved.
 * Viewpage title 实例
 */
public class ViewPageTitleBean {
    private String titlename;
    private String type;

    public String getTitlename() {
        return titlename == null ? "" : titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
