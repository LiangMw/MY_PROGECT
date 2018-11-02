package com.integrate.mingweidev.mvp.presenter;

import android.content.Context;

import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.BookBean;
import com.integrate.mingweidev.mvp.contract.CBook;
import com.integrate.mingweidev.mvp.model.MFragmentTest;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

/**
 * Created by 梁明伟 on 2018/11/2.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PFragmentTest extends BasePresenter<CBook.IVBook,MFragmentTest> implements CBook.IPBook{


    public PFragmentTest(Context mContext, CBook.IVBook mView) {
        super(mContext, mView,new MFragmentTest());
    }

    @Override
    public void pBook() {
        mView.showLoading();
        mModel.mFragmentBook(new RxObservable<BookBean>() {
            @Override
            public void onSuccess(BookBean bookBean) {

                mView.hideLoading();
                mView.vBookSuccess(bookBean);
            }

            @Override
            public void onFail(String reason) {

            }
        });

    }
}
