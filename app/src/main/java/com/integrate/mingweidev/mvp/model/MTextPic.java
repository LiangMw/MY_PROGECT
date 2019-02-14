package com.integrate.mingweidev.mvp.model;

import com.allen.library.RxHttpUtils;
import com.integrate.mingweidev.api.DevMvpService;
import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

import java.util.Map;

/**
 * Created by 梁明伟 on 2019/2/14.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MTextPic extends BaseModel {

    public void getpictext(RxObservable observable, Map<String,String> image) {
        RxHttpUtils.createApi(DevMvpService.class)
                .picbook(image)
                .compose(RxTransformer.switchSchedulers(MTextPic.this))
                .subscribe(observable);
    }
}
