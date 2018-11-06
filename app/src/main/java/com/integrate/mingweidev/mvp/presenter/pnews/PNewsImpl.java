package com.integrate.mingweidev.mvp.presenter.pnews;

import android.content.Context;

import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.NewsListBean;
import com.integrate.mingweidev.mvp.contract.CNewsList;
import com.integrate.mingweidev.mvp.model.mnews.MNews;
import com.integrate.mingweidev.utils.NetworkUtils;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

/**
 * Created by 梁明伟 on 2018/11/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PNewsImpl extends BasePresenter<CNewsList.IVNewsList, MNews> implements CNewsList.IPNewsList {


    public PNewsImpl(Context mContext, CNewsList.IVNewsList mView) {
        super(mContext, mView, new MNews());
    }


    @Override
    public void newslist() {
        mView.showLoading();
        if (!NetworkUtils.isConnected()) {
            mView.neterror();
        }
        mModel.newsList(new RxObservable<NewsListBean>() {

            @Override
            public void onSuccess(NewsListBean newsListBean) {
                mView.newslistSuccess(newsListBean);
                mView.hideLoading();
                //这里拿到网络获取到的数据可以
                //在这个类里面进行逻辑处理然后调用View层的 listsuccess方法把处理好的数据传回去，做到了高度解耦

            }

            @Override
            public void onFail(String reason) {
                mView.newslistError(reason);
                mView.hideLoading();
            }
        }, "520");

    }
}
