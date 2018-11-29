package com.integrate.mingweidev.mvp.view.adapter.ad_music;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.integrate.mingweidev.mvp.bean.SheetInfo;

/**
 * Created by 梁明伟 on 2018/11/29.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MultipleItem implements MultiItemEntity {

    public static final int TYPE_PROFILE = 0;
    public static final int TYPE_MUSIC_LIST = 1;
    public SheetInfo data;
    private int itemType;

    public MultipleItem(int itemType, SheetInfo data) {
        this.itemType = itemType;
        this.data = data;
    }

    public SheetInfo getData() {
        return data;
    }

    public void setData(SheetInfo data) {
        this.data = data;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
