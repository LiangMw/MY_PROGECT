package com.integrate.mingweidev.mvp.bean;

import java.io.Serializable;

/**
 * Created by 梁明伟 on 2018/12/27.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class TokenBean implements Serializable{

    /**
     * result : {"createtime":"2018-12-27 18:24:44","guid":"3eb16e2e237a44f28a72d7ca0723ff07","id":4,"updatetime":"2018-12-27 18:25:02","token":"coVNRgyFCq"}
     * msg : 成功
     * code : 0
     */

    private ResultBean result;
    private String msg;
    private String code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * createtime : 2018-12-27 18:24:44
         * guid : 3eb16e2e237a44f28a72d7ca0723ff07
         * id : 4
         * updatetime : 2018-12-27 18:25:02
         * token : coVNRgyFCq
         */

        private String createtime;
        private String guid;
        private int id;
        private String updatetime;
        private String token;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
