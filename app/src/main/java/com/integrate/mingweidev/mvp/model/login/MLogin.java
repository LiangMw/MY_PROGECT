package com.integrate.mingweidev.mvp.model.login;

import com.allen.library.RxHttpUtils;
import com.integrate.mingweidev.api.DevMvpService;
import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

/**
 * Created by 梁明伟 on 2018/12/27.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MLogin extends BaseModel {

    public void register(RxObservable observable, String tel,String pwd) {
//        apiService().newsList("520")
//                .compose(RxTransformer.switchSchedulers(MNews.this))
//                .subscribe(observable);
        RxHttpUtils.createApi(DevMvpService.class)
                .register(tel,pwd)
                .compose(RxTransformer.switchSchedulers(MLogin.this))
                .subscribe(observable);
    }
    public void login(RxObservable observable, String tel,String pwd) {
//        apiService().newsList("520")
//                .compose(RxTransformer.switchSchedulers(MNews.this))
//                .subscribe(observable);
        RxHttpUtils.getInstance().createApi(DevMvpService.class)
                .login(tel,pwd)
                .compose(RxTransformer.switchSchedulers(MLogin.this))
                .subscribe(observable);
    }
    public void userinfo(RxObservable observable, String guid) {
//        apiService().newsList("520")
//                .compose(RxTransformer.switchSchedulers(MNews.this))
//                .subscribe(observable);
        RxHttpUtils.createApi(DevMvpService.class)
                .userinfo()
                .compose(RxTransformer.switchSchedulers(MLogin.this))
                .subscribe(observable);
    }

}
