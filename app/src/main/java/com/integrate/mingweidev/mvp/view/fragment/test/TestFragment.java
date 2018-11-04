package com.integrate.mingweidev.mvp.view.fragment.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.bean.BookBean;
import com.integrate.mingweidev.mvp.contract.CBook;
import com.integrate.mingweidev.mvp.presenter.PFragmentTest;
import com.integrate.mingweidev.utils.LoadingHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 梁明伟 on 2018/11/2.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class TestFragment extends BaseFragment<PFragmentTest> implements CBook.IVBook {

    @BindView(R.id.tv)
    TextView tv;

    @BindView(R.id.bt_jjj)
    Button btJjj;

    Unbinder unbinder;
    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.f1;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {
        mPresenter = new PFragmentTest(getActivity(), this);
    }

    /**
     * 初始化
     */
    @Override
    protected void initView() {
        btJjj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.pBook();
            }
        });
    }

    @Override
    public void vBookSuccess(BookBean bean) {
        tv.setText(bean.getFemale().get(0).getName());
    }

    @Override
    public void vBookError(String reason) {

    }

    @Override
    public void showLoading() {
        LoadingHelper.getInstance().showLoading(getActivity());
    }

    @Override
    public void hideLoading() {
        LoadingHelper.getInstance().hideLoading();
    }

    @Override
    public void neterror() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
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
