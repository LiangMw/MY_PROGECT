package com.integrate.mingweidev.mvp.model.mnews;

import com.allen.library.RxHttpUtils;
import com.integrate.mingweidev.api.DevMvpService;
import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

/**
 * Created by 梁明伟 on 2018/11/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MNews extends BaseModel {

    public void newsList(RxObservable observable,String newsid) {
//        apiService().newsList("520")
//                .compose(RxTransformer.switchSchedulers(MNews.this))
//                .subscribe(observable);
        RxHttpUtils.createApi(DevMvpService.class)
                .newsList(newsid)
                .compose(RxTransformer.switchSchedulers(MNews.this))
                .subscribe(observable);
    }

}
