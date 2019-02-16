package com.integrate.mingweidev.mvp.contract;

import com.integrate.mingweidev.mvp.base.BaseResponse;
import com.integrate.mingweidev.mvp.base.IBaseView;

/**
 * Created by 梁明伟 on 2019/2/14.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface CResult<T extends BaseResponse> extends IBaseView {

    void success(T e);
    void error(String msg);

}
