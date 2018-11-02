package com.integrate.mingweidev.mvp.model;

import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

/**
 * Created by 梁明伟 on 2018/11/2.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MFragmentTest extends BaseModel {

    public void mFragmentBook(RxObservable rxObservable){

        apiService().bookClassifyfragment()
                .compose(RxTransformer.switchSchedulers(MFragmentTest.this))
                .subscribe(rxObservable);

    }

}
