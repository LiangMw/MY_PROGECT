package com.integrate.mingweidev.mvp.contract;


import com.integrate.mingweidev.mvp.base.IBasePresenter;
import com.integrate.mingweidev.mvp.base.IBaseView;
import com.integrate.mingweidev.mvp.bean.BannerBean;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;

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

        void getsonglistSuccess(OnlineMusicList onlineMusicList);
        void getsonglisterror(String reason);

    }

    interface IPTypeMusic extends IBasePresenter{
        void  getSongList();
    }

    interface IVTypeMusic extends IBaseView{
        void getSongListSuccess(OnlineMusicList Bean);
        void getSongListError(String resson);
    }
}