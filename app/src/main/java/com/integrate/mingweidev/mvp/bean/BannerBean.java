package com.integrate.mingweidev.mvp.bean;

import java.util.List;

/**
 * Created by 梁明伟 on 2018/11/9.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class BannerBean {
    /**
     * result : [{"picurl":"https://mingwei-1256192892.cos.ap-chengdu.myqcloud.com/1541755400(1).jpg?q-sign-algorithm=sha1&q-ak=AKIDBbAvTVp0e72HaaY2GdRkJXmXpKUtmpLZ&q-sign-time=1541755615;1541757415&q-key-time=1541755615;1541757415&q-header-list=&q-url-param-list=&q-signature=f12b78c5163cb4898212dcee734cef9bac913931&x-cos-security-token=57ecfc52e9f88ef9f77ce13aa69a052851ca994b10001&response-content-disposition=attachment","id":1,"type":0},{"picurl":"https://mingwei-1256192892.cos.ap-chengdu.myqcloud.com/1541755428(1).jpg?q-sign-algorithm=sha1&q-ak=AKIDCWpmD40ItlQzFbJA1oLMwOxaBxJZX5FI&q-sign-time=1541756304;1541758104&q-key-time=1541756304;1541758104&q-header-list=&q-url-param-list=&q-signature=1e4707af33f124abc5544fa7284045db8fc14681&x-cos-security-token=90898bea5806cf58f9b2f20cb1af73887785fdc710001&response-content-disposition=attachment","id":2,"type":0},{"picurl":"https://mingwei-1256192892.cos.ap-chengdu.myqcloud.com/1541755462.jpg?q-sign-algorithm=sha1&q-ak=AKIDUustaWo2RCLhjm2vWffCh40zZkxEzfxi&q-sign-time=1541756323;1541758123&q-key-time=1541756323;1541758123&q-header-list=&q-url-param-list=&q-signature=0263f498709b9325abf4ff5d127cad4eaff8dc85&x-cos-security-token=78abdca02ccd9f779e44233bb13efbf6147a82b610001&response-content-disposition=attachment","id":3,"type":0},{"picurl":"https://mingwei-1256192892.cos.ap-chengdu.myqcloud.com/1541755512(1).jpg?q-sign-algorithm=sha1&q-ak=AKIDKv2grwJg3DREoUDvZNOsZfUKfRW9GaDv&q-sign-time=1541756389;1541758189&q-key-time=1541756389;1541758189&q-header-list=&q-url-param-list=&q-signature=bac48144f99183edbb82a0ec69a5f4fa6fa65cfc&x-cos-security-token=d8e0dd5b5e5e41094e4fc5cad95844c1ecf677d110001&response-content-disposition=attachment","id":4,"type":0}]
     * msg : 成功
     * code : 0
     */

    private String msg;
    private String code;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * picurl : https://mingwei-1256192892.cos.ap-chengdu.myqcloud.com/1541755400(1).jpg?q-sign-algorithm=sha1&q-ak=AKIDBbAvTVp0e72HaaY2GdRkJXmXpKUtmpLZ&q-sign-time=1541755615;1541757415&q-key-time=1541755615;1541757415&q-header-list=&q-url-param-list=&q-signature=f12b78c5163cb4898212dcee734cef9bac913931&x-cos-security-token=57ecfc52e9f88ef9f77ce13aa69a052851ca994b10001&response-content-disposition=attachment
         * id : 1
         * type : 0
         */

        private String picurl;
        private int id;
        private int type;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
