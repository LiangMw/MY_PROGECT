package com.integrate.mingweidev.mvp.view.fragment.music;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.mvp.bean.SheetInfo;
import com.integrate.mingweidev.mvp.contract.CMusic;
import com.integrate.mingweidev.mvp.model.music.MMusicImpl;
import com.integrate.mingweidev.mvp.presenter.pmusic.PMusicListImpl;
import com.integrate.mingweidev.mvp.view.adapter.ad_music.MusicListAdapter;
import com.integrate.mingweidev.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 梁明伟 on 2018/12/17.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class NetMusicListFragment extends BaseFragment<PMusicListImpl> implements CMusic.IVTypeMusic{

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.cl_root)
    CoordinatorLayout clRoot;
    @BindView(R.id.rv_classify)
    RecyclerView rv_classify;

    private MusicListAdapter adapter;

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_netmusiclist;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {
        mPresenter = new PMusicListImpl(getActivity(),this,new MMusicImpl());
    }

    /**
     * 初始化
     */
    @Override
    protected void initView() {

        SheetInfo item = (SheetInfo)getActivity().getIntent().getExtras().getBundle(Constant.DATA_KEY).getSerializable(Constant.MUSIC_LIST_TYPE);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getActivity().getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        toolbarLayout.setTitle("用户信息");
        Glide.with(mContext).load("http://mallcomment.holdsoft.cn/1510224399351")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(15)).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true))
                .into(ivAvatar);

        Map<String,String> param = new HashMap<>();
        param.put(Constant.PARAM_TYPE,item.getType());
        param.put(Constant.PARAM_OFFSET,"0");
        param.put(Constant.PARAM_METHOD,Constant.METHOD_GET_MUSIC_LIST);
        param.put(Constant.PARAM_SIZE,"30");
        mPresenter.getSongList(param);

    }

    @Override
    public void setTitleBar(ActionBarRes mActionBarRes) {
        super.setTitleBar(mActionBarRes);
        mActionBarRes.titleBarVisible = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void getSongListSuccess(OnlineMusicList bean) {

//        mRefreshLayout.finishRefresh();
//        mRefreshLayout.finishLoadmore();
//        loadinglayout.setStatus(LoadingLayout.Success);

//        if(bean !=null && bean.getFemale().size()>0) {
//            datas.addAll(bean.getFemale());
//        }else{
//            loadinglayout.setStatus(LoadingLayout.Empty);
//        }
        if(adapter == null) {
            adapter = new MusicListAdapter(bean.getSong_list());
            rv_classify.setLayoutManager(new LinearLayoutManager(mContext));
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            rv_classify.setAdapter(adapter);
            adapter.setOnItemClickListener((adapter, view, position) -> {
//                Bundle bundle = new Bundle();
//                bundle.putString("getder", getder);
//                bundle.putString("titleName", mClassifyBeans.get(position).getName());
//                startActivity(BookListActivity.class, bundle);
            });
        }else{
            adapter.setNewData(bean.getSong_list());
        }
    }

    @Override
    public void getSongListError(String resson) {

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
