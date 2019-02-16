package com.integrate.mingweidev.mvp.bean;

import com.integrate.mingweidev.mvp.base.BaseResponse;

/**
 * Created by 梁明伟 on 2019/1/10.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PicBookBean extends BaseResponse{

    /**
     * result : {"text":"3:24TR9第一章扫地小厮天蒙蒙亮,杨开就醒了,稍微洗漱一番便拿着墙角边的扫帚走出了独居的小屋。站在门口伸了个懒腰,看了一眼天际边浮现的一抹鱼肚白,闭目凝神享受了片刻的安宁,随即睁开眼帘的活,一边维持生计一边苦苦修炼。"}
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
         * text : 3:24TR9第一章扫地小厮天蒙蒙亮,杨开就醒了,稍微洗漱一番便拿着墙角边的扫帚走出了独居的小屋。站在门口伸了个懒腰,看了一眼天际边浮现的一抹鱼肚白,闭目凝神享受了片刻的安宁,随即睁开眼帘的活,一边维持生计一边苦苦修炼。
         */

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
