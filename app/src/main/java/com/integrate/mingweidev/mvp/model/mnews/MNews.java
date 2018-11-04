package com.integrate.mingweidev.mvp.model.mnews;

import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

/**
 * Created by 梁明伟 on 2018/11/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MNews extends BaseModel {

    public void newsList(RxObservable observable){
        apiService().newsList()
                .compose(RxTransformer.switchSchedulers(MNews.this))
                .subscribe(observable);
    }

}
