package com.integrate.mingweidev.mvp.view.fragment.music;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.bean.BannerBean;
import com.integrate.mingweidev.mvp.contract.CMusic;
import com.integrate.mingweidev.mvp.presenter.pmusic.PMusicImpl;
import com.integrate.mingweidev.utils.ScreenUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 梁明伟 on 2018/11/8.
 * Copyright © 2018年 CETC. All rights reserved.
 * 网络歌曲
 */
public class NetMusicFragment extends BaseFragment<PMusicImpl> implements CMusic.IVMusic{

    @BindView(R.id.banner)
    XBanner banner;

    List<BannerBean.ResultBean> data = new ArrayList<>();


    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_music_net;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {
        mPresenter = new PMusicImpl(mContext,this);
    }


    /**
     * 初始化
     */
    @Override
    protected void initView() {
        banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(getActivity()) / 2));
        initBanner();
        mPresenter.getbanners();
    }

    /**
     * 初始化XBanner
     */
    private void initBanner() {
        //设置广告图片点击事件
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                ToastUtils.show("点击了第" + (position + 1) + "图片");
            }
        });
        //加载广告图片
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //此处适用Fresco加载图片，可自行替换自己的图片加载框架
                ImageView draweeView = (ImageView) view;
                BannerBean.ResultBean bann = (BannerBean.ResultBean) model;
                ImageLoadManage.getInstance().display(getActivity(), draweeView, bann.getPicurl());
            }
        });
    }

    @Override
    public void getbannerSuccess(BannerBean bannerBean) {
        data =  bannerBean.getResult();
        banner.setAutoPlayAble(data.size() > 1);
        banner.setData(data, null);
    }

    @Override
    public void getbannererror(String reason) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void neterror() {

    }
}
