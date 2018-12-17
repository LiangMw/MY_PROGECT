package com.integrate.mingweidev.mvp.presenter.pmusic;

import android.content.Context;

import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.mvp.contract.CMusic;
import com.integrate.mingweidev.mvp.model.music.MMusicImpl;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

import java.util.Map;

/**
 * Created by 梁明伟 on 2018/12/17.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PMusicListImpl extends BasePresenter<CMusic.IVTypeMusic,MMusicImpl> {

    public PMusicListImpl(Context mContext, CMusic.IVTypeMusic mView, MMusicImpl mModel) {
        super(mContext, mView, mModel);
    }

    /**
     * 获取歌曲列表
     */
    public void getSongList(Map<String,String> param) {

        mView.showLoading();
        mModel.getSongList(new RxObservable<OnlineMusicList>() {

            @Override
            public void onSuccess(OnlineMusicList Bean) {
                mView.hideLoading();
                mView.getSongListSuccess(Bean);
            }

            @Override
            public void onFail(String reason) {
                mView.hideLoading();
                mView.getSongListError(reason);
            }
        },param);
    }
}
