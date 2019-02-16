package com.integrate.mingweidev.mvp.presenter;

import android.content.Context;

import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.PicBookBean;
import com.integrate.mingweidev.mvp.contract.CResult;
import com.integrate.mingweidev.mvp.model.MTextPic;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

import java.util.Map;

/**
 * Created by 梁明伟 on 2019/2/14.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PTextPic extends BasePresenter<CResult,MTextPic> {

    public PTextPic(Context mContext, CResult mView) {
        super(mContext, mView, new MTextPic());
    }

    public void getpictext(Map<String,String> image){

        mView.showLoading();
        mModel.getpictext(new RxObservable<PicBookBean>() {
            @Override
            public void onSuccess(PicBookBean o) {
                mView.success(o);
                mView.hideLoading();
            }

            @Override
            public void onFail(String reason) {
                mView.error(reason);
                mView.hideLoading();
            }
        },image);


    }
}
