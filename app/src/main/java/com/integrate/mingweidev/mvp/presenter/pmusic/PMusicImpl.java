package com.integrate.mingweidev.mvp.presenter.pmusic;

import android.content.Context;
import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.BannerBean;
import com.integrate.mingweidev.mvp.contract.CMusic;
import com.integrate.mingweidev.mvp.model.music.MMusicImpl;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;


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
}
