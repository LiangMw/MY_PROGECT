package com.integrate.mingweidev.mvp.model.music;

import com.allen.library.RxHttpUtils;
import com.allen.library.cookie.store.MemoryCookieStore;
import com.integrate.mingweidev.api.DevMvpService;
import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

import java.util.Map;

/**
 * Created by Liang_Lu on 2017/12/21.
 *
 * @author LuLiang
 * @github https://github.com/LiangLuDev
 */

public class MMusicImpl extends BaseModel {


    public void getBanner(RxObservable observable) {

        RxHttpUtils
                .getSInstance()
                .baseUrl("http://118.24.213.53:8080/jfinal_demo/")
//                .addHeaders(headerMaps)
                .cache(true)
                .cachePath("cachePath", 1024 * 1024 * 100)
                .sslSocketFactory()
                .cookieType(new MemoryCookieStore())
                .writeTimeout(10)
                .readTimeout(10)
                .connectTimeout(10)
                .log(true)
                .createSApi(DevMvpService.class)
                .bannerList()
                .compose(RxTransformer.switchSchedulers(MMusicImpl.this))
                .subscribe(observable);

    }

    public void getSongList(RxObservable observable, Map<String,String> param) {

        RxHttpUtils
                .getSInstance()
                .baseUrl(Constant.BASE_URL)
//                .addHeaders(headerMaps)
                .cache(true)
                .cachePath("cachePath", 1024 * 1024 * 100)
                .sslSocketFactory()
                .cookieType(new MemoryCookieStore())
                .writeTimeout(10)
                .readTimeout(10)
                .connectTimeout(10)
                .log(true)
                .createSApi(DevMvpService.class)
                .songList(param)
                .compose(RxTransformer.switchSchedulers(MMusicImpl.this))
                .subscribe(observable);

    }

}
