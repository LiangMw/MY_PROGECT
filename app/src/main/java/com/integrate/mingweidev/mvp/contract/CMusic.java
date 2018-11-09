package com.integrate.mingweidev.mvp.contract;


import com.integrate.mingweidev.mvp.base.IBasePresenter;
import com.integrate.mingweidev.mvp.base.IBaseView;
import com.integrate.mingweidev.mvp.bean.BannerBean;

/**
 * Created by Liang_Lu on 2017/12/21.
 *
 * @author LuLiang
 * @github https://github.com/LiangLuDev
 */

public interface CMusic {

    interface IPMusic extends IBasePresenter {

        void getbanners();

    }

    interface IVMusic extends IBaseView {

        void getbannerSuccess(BannerBean bannerBean);
        void getbannererror(String reason);

    }
}