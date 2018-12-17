package com.integrate.mingweidev.mvp.view.fragment.music;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.bean.BannerBean;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.mvp.bean.SheetInfo;
import com.integrate.mingweidev.mvp.contract.CMusic;
import com.integrate.mingweidev.mvp.presenter.pmusic.PMusicImpl;
import com.integrate.mingweidev.mvp.view.adapter.ad_music.MusicTypeListAdapter;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 梁明伟 on 2018/11/8.
 * Copyright © 2018年 CETC. All rights reserved.
 * 网络歌曲
 */
public class NetMusicFragment extends BaseFragment<PMusicImpl> implements CMusic.IVMusic {

    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;
    Unbinder unbinder;
    String b;

    List<BannerBean.ResultBean> data = new ArrayList<>();
    private List<SheetInfo> mSongLists = new ArrayList<>();
    private MusicTypeListAdapter musiclistadapter;

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
        mPresenter = new PMusicImpl(mContext, this);
    }


    /**
     * 初始化
     */
    @Override
    protected void initView() {
//        banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(getActivity()) / 2));
//        initBanner();
//        mPresenter.getbanners();

        if (mSongLists.isEmpty()||mSongLists.size()<1) {
            String[] titles = getResources().getStringArray(R.array.online_music_list_title);
            String[] types = getResources().getStringArray(R.array.online_music_list_type);
            for (int i = 0; i < titles.length; i++) {
                SheetInfo info = new SheetInfo();
                info.setItemtype(types[i].equals("#")? Constant.TYPE_PROFILE:Constant.TYPE_MUSIC_LIST);
                info.setType(types[i]);
                info.setTitle(titles[i]);
                mSongLists.add(info);
            }
        }

        if(musiclistadapter == null) {
            musiclistadapter = new MusicTypeListAdapter(getActivity(),mSongLists);
            rvClassify.setLayoutManager(new LinearLayoutManager(mContext));
            musiclistadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            rvClassify.setAdapter(musiclistadapter);
        }else{
            musiclistadapter.setNewData(mSongLists);
        }


//        mPresenter.getSongList();
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
        data = bannerBean.getResult();
        banner.setAutoPlayAble(data.size() > 1);
        banner.setData(data, null);
    }

    @Override
    public void getbannererror(String reason) {

    }

    @Override
    public void getsonglistSuccess(OnlineMusicList onlineMusicList) {

    }

    @Override
    public void getsonglisterror(String reason) {

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
