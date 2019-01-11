package com.integrate.mingweidev.mvp.view.fragment.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.bean.BookBean;
import com.integrate.mingweidev.mvp.bean.PicBookBean;
import com.integrate.mingweidev.mvp.contract.CTest;
import com.integrate.mingweidev.mvp.presenter.PFragmentTest;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.ImageUtils;
import com.integrate.mingweidev.utils.LoadingHelper;
import com.integrate.mingweidev.utils.LogUtils;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 梁明伟 on 2018/11/2.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class TestFragment extends BaseFragment<PFragmentTest> implements CTest.IVBook {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bt_jjj)
    Button btJjj;
    @BindView(R.id.bt_ddd)
    Button bt_ddd;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;

    Unbinder unbinder;

    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数

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
                LogUtils.e(Constant.picpath);
                LogUtils.e(ImageUtils.imageToBase64Str(Constant.picpath));
                Map<String, String> bodys = new HashMap<String, String>();
                bodys.clear();
//                bodys.put("image","https://appbundle.holdsoft.cn/Screenshot_1547090720.png");
                bodys.put("image",Constant.picpath);
                mPresenter.ppicBook(bodys);

            }
        });

        bt_ddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RxHttpUtils.cancel("download");
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
    public void Prosess(String string) {
        tv.setText(string);
    }

    @Override
    public void vPicBookSuccess(PicBookBean bookBean) {
        tv.setText(bookBean.getResult());
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
