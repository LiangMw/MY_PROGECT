package com.integrate.mingweidev.mvp.model;

import com.allen.library.RxHttpUtils;
import com.allen.library.cookie.store.MemoryCookieStore;
import com.allen.library.download.DownloadObserver;
import com.integrate.mingweidev.api.DevMvpService;
import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 梁明伟 on 2018/11/2.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MFragmentTest extends BaseModel {
    private HashMap<String, Object> headerMaps = new HashMap<>();

    public void newsList(DownloadObserver observer) {

        /*RxHttpUtils.downloadFile("https://appbundle.holdsoft.cn/holdstore_18102701.apk")
                .compose(RxTransformer.switchSchedulers(MFragmentTest.this))
                .subscribe(observer);*/

    }

    public void getPicContent(RxObservable observable, Map<String, String> param) {

        headerMaps.clear();
//        headerMaps.put("User-Agent", Build.MODEL+"/"+Build.VERSION.RELEASE);
        headerMaps.put("Authorization", "APPCODE " + "78ddd918b0fc4a83a5f9b1e20ca1e83a");
        RxHttpUtils
                .getSInstance()
                .baseUrl("http://txtbookocr.market.alicloudapi.com/")
                .cache(true)
                .cachePath("cachePath", 1024 * 1024 * 100)
                .sslSocketFactory()
                .cookieType(new MemoryCookieStore())
                .writeTimeout(10)
                .readTimeout(10)
                .connectTimeout(10)
                .log(true)
                .addHeaders(headerMaps)
                .createSApi(DevMvpService.class)
                .picbook(param)
                .compose(RxTransformer.switchSchedulers(MFragmentTest.this))
                .subscribe(observable);

    }

}
