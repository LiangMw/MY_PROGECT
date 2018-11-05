package com.integrate.mingweidev.mvp.contract;

import com.integrate.mingweidev.mvp.base.IBasePresenter;
import com.integrate.mingweidev.mvp.base.IBaseView;
import com.integrate.mingweidev.mvp.bean.NewsListBean;

/**
 * Created by 梁明伟 on 2018/11/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface CNewsList {

    interface IPNewsList extends IBasePresenter {

        void newslist();

    }

    interface IVNewsList extends IBaseView{

        void newslistSuccess(NewsListBean bean);

        void newslistError(String reason);
    }
}
