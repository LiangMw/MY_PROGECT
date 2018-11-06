package com.integrate.mingweidev.mvp.model;

import com.allen.library.RxHttpUtils;
import com.allen.library.download.DownloadObserver;
import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

/**
 * Created by 梁明伟 on 2018/11/2.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MFragmentTest extends BaseModel {

    public void newsList(DownloadObserver observer) {

        RxHttpUtils.downloadFile("https://appbundle.holdsoft.cn/holdstore_18102701.apk")
                .compose(RxTransformer.switchSchedulers(MFragmentTest.this))
                .subscribe(observer);

    }


}
