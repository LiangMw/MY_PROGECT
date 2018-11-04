package com.integrate.mingweidev.mvp.model;

import com.integrate.mingweidev.mvp.base.BaseModel;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;
import com.integrate.mingweidev.utils.rxhelper.RxTransformer;

/**
 * Created by Liang_Lu on 2017/12/21.
 * model层用于处理数据,例：网络数据 数据库缓存数据 在此类处理以后返回给P层
 */

public class MBookImpl extends BaseModel {

    public void mBook(RxObservable rxObservable) {
        apiService()
                .bookClassify("mingweipath")
                .compose(RxTransformer.switchSchedulers(this))
                .subscribe(rxObservable);
    }

}

