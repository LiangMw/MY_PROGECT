package com.integrate.mingweidev.mvp.contract;

import com.integrate.mingweidev.mvp.base.BaseResponse;
import com.integrate.mingweidev.mvp.base.IBasePresenter;
import com.integrate.mingweidev.mvp.base.IBaseView;
import com.integrate.mingweidev.mvp.bean.UserInfoBean;

/**
 * Created by 梁明伟 on 2018/12/28.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class CLogin {

    public interface IPLogin extends IBasePresenter{
        void login(String tel,String pwd);
        void getuserinfo(String guid);
    }

    public interface IVLogin<T> extends IBaseView{
        void success(T bean);
        void error(String msg);
    }

    public interface IVUserInfo extends IBaseView{
        void getuserinfosuccess(UserInfoBean bean);
        void getuserinfoerror(String msg);
    }
    public interface IVRegister extends IBaseView{
        void registersuccess(BaseResponse bean);
        void registererror(String msg);
    }

}
