package com.integrate.mingweidev.mvp.view.fragment.textpicture;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;

/**
 * Created by 梁明伟 on 2019/1/30.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class TextPicFragment extends BaseFragment {

    TakePhoto takePhoto=new TakePhotoImpl(getActivity(),this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_textpic;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {

    }

    /**
     * 初始化
     */
    @Override
    protected void initView() {

    }

    @Override
    public void setTitleBar(ActionBarRes mActionBarRes) {
        super.setTitleBar(mActionBarRes);
        mActionBarRes.mTitle = getString(R.string.fc_textpic);
    }

}
