package com.integrate.mingweidev.mvp.view.fragment.files;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 梁明伟 on 2018/10/25.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.tv)
    TextView tv;
    Unbinder unbinder;

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
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.f1;
    }

    @Override


    public void onPause() {
        super.onPause();
    }

    @Override
    public void setTitleBar(ActionBarRes mActionBarRes) {
        super.setTitleBar(mActionBarRes);
        mActionBarRes.mTitle = "新标题";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
