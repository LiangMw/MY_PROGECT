package com.integrate.mingweidev.mvp.presenter.login;

import android.content.Context;

import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.bean.TokenBean;
import com.integrate.mingweidev.mvp.bean.UserInfoBean;
import com.integrate.mingweidev.mvp.contract.CLogin;
import com.integrate.mingweidev.mvp.model.login.MLogin;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

/**
 * Created by 梁明伟 on 2018/12/28.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PLogin extends BasePresenter<CLogin.IVLogin, MLogin> implements CLogin.IPLogin{


    public PLogin(Context mContext, CLogin.IVLogin mView, MLogin mModel) {
        super(mContext, mView, mModel);
    }

    @Override
    public void login(String tel, String pwd) {
        mView.showLoading();
        mModel.login(new RxObservable<TokenBean>() {
            @Override
            public void onSuccess(TokenBean o) {
                mView.success(o);
                mView.hideLoading();
            }

            @Override
            public void onFail(String reason) {
                mView.error(reason);
                mView.hideLoading();
            }
        }, tel, pwd);
    }

    @Override
    public void getuserinfo(String guid) {
        mView.showLoading();
        mModel.userinfo(new RxObservable<UserInfoBean>() {

            @Override
            public void onSuccess(UserInfoBean bean) {
                mView.hideLoading();
                mView.success(bean);

            }

            @Override
            public void onFail(String reason) {
                mView.hideLoading();
                mView.error(reason);
            }
        },guid);
    }

}
