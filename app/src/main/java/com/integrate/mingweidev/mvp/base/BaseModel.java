package com.integrate.mingweidev.mvp.base;


import com.allen.library.RxHttpUtils;
import com.integrate.mingweidev.api.DevMvpService;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Liang_Lu on 2017/12/21.
 */

public abstract class BaseModel {


    public CompositeDisposable mDisposable = new CompositeDisposable();

    /**
     * 初始化调用网络请求
     * @return
     */
    public DevMvpService apiService() {
        return  RxHttpUtils.createApi(DevMvpService.class);
    }
//    public DevMvpService apiService() {
//        return DevMvpApi.createApi().createSApi(DevMvpService.class);
//    }
    /**
     * 取消网络请求
     */
    public void onDestroy() {

        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable.clear();
        }
    }
}
