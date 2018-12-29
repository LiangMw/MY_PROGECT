package com.integrate.mingweidev.mvp.base;

/**
 * Created by 梁明伟 on 2018/12/27.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class BaseResponse {
    /**
     * msg : 成功
     * code : 0
     */
    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
