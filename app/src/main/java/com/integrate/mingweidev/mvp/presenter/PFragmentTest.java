package com.integrate.mingweidev.mvp.presenter;

import android.content.Context;

import com.allen.library.download.DownloadObserver;
import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.PicBookBean;
import com.integrate.mingweidev.mvp.contract.CBook;
import com.integrate.mingweidev.mvp.contract.CTest;
import com.integrate.mingweidev.mvp.model.MFragmentTest;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

import java.util.Map;

/**
 * Created by 梁明伟 on 2018/11/2.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PFragmentTest extends BasePresenter<CTest.IVBook,MFragmentTest> implements CBook.IPBook{


    public PFragmentTest(Context mContext, CTest.IVBook mView) {
        super(mContext, mView,new MFragmentTest());
    }

    @Override
    public void pBook() {
        mModel.newsList(new DownloadObserver("mingweitest_12.apk") {

            @Override
            protected String setTag() {
                return "download";
            }

            @Override
            protected void onError(String s) {

            }

            @Override
            protected void onSuccess(long l, long l1, float v, boolean b, String s) {
               if(b) {

                   mView.Prosess("下载完成"+s);
               }
               else {
                   mView.Prosess("下 载中：" + v + "%");
               }

            }
        });

    }

    @Override
    public void ppicBook(Map<String,String> image) {
        mModel.getPicContent(new RxObservable<PicBookBean>() {

            @Override
            public void onSuccess(PicBookBean picBookBean) {
                mView.vPicBookSuccess(picBookBean);
            }

            @Override
            public void onFail(String reason) {

            }
        },image);

    }


}
