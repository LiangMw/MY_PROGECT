package com.integrate.mingweidev.mvp.presenter.pmusic;

import android.content.Context;
import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.BannerBean;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.mvp.contract.CMusic;
import com.integrate.mingweidev.mvp.model.music.MMusicImpl;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

import java.util.Map;


/**
 * Created by Liang_Lu on 2017/12/21.
 *
 * @author LuLiang
 * @github https://github.com/LiangLuDev
 */

public class PMusicImpl extends BasePresenter<CMusic.IVMusic, MMusicImpl> implements CMusic.IPMusic {


    public PMusicImpl(Context mContext, CMusic.IVMusic mView) {
        super(mContext, mView, new MMusicImpl());
    }

    /**
     * 获取轮播图
     */
    public void getbanners() {
        
        mView.showLoading();
        mModel.getBanner(new RxObservable<BannerBean>() {

            @Override
            public void onSuccess(BannerBean bannerBean) {
                mView.getbannerSuccess(bannerBean);
            }

            @Override
            public void onFail(String reason) {
                mView.hideLoading();
                mView.getbannererror(reason);
            }
        });
    }

    /**
     * 获取歌曲列表
     */
    public void getSongList(Map<String,String> param) {

        mView.showLoading();
        mModel.getSongList(new RxObservable<OnlineMusicList>() {

            @Override
            public void onSuccess(OnlineMusicList Bean) {
                mView.getsonglistSuccess(Bean);
            }

            @Override
            public void onFail(String reason) {
                mView.hideLoading();
                mView.getsonglisterror(reason);
            }
        },param);
    }
}
