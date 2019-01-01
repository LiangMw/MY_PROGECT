package com.integrate.mingweidev.mvp.bean;

import com.integrate.mingweidev.mvp.base.BaseResponse;

/**
 * Created by 梁明伟 on 2018/12/28.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class UserInfoBean extends BaseResponse {

    /**
     * result : {"createtime":null,"idnumber":null,"guid":"393e171c39b445ffac4dad808fff214d","memo":null,"facepic":null,"tel":"15733189728","id":4,"updatetime":null,"username":"用户2103"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * createtime : null
         * idnumber : null
         * guid : 393e171c39b445ffac4dad808fff214d
         * memo : null
         * facepic : null
         * tel : 15733189728
         * id : 4
         * updatetime : null
         * username : 用户2103
         */

        private String createtime;
        private String idnumber;
        private String guid;
        private String memo;
        private String facepic;
        private String tel;
        private int id;
        private String updatetime;
        private String username;

        public String getCreatetime() {
            return createtime == null ? "" : createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIdnumber() {
            return idnumber == null ? "" : idnumber;
        }

        public void setIdnumber(String idnumber) {
            this.idnumber = idnumber;
        }

        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getMemo() {
            return memo == null ? "" : memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getFacepic() {
            return facepic == null ? "" : facepic;
        }

        public void setFacepic(String facepic) {
            this.facepic = facepic;
        }

        public String getTel() {
            return tel == null ? "" : tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUpdatetime() {
            return updatetime == null ? "" : updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getUsername() {
            return username == null ? "" : username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
