package com.integrate.mingweidev.mvp.view.fragment.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.bean.NewsListBean;
import com.integrate.mingweidev.mvp.contract.CNewsList;
import com.integrate.mingweidev.mvp.presenter.pnews.PNewsImpl;
import com.integrate.mingweidev.mvp.view.adapter.news.NewsListAdapter;
import com.integrate.mingweidev.utils.LoadingHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.weavey.loading.lib.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 梁明伟 on 2018/11/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class NewsMainFragment extends BaseFragment<PNewsImpl> implements CNewsList.IVNewsList {

    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;
    @BindView(R.id.loadinglayout)
    LoadingLayout loadinglayout;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    String tabName;
    NewsListAdapter newsListAdapter;
    private List<NewsListBean.FemaleBean> datas = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_newslist;
    }

    @Override
    public void createPresenter() {
        mPresenter = new PNewsImpl(mContext, this);

    }

    @Override
    protected void initView() {
        mPresenter.newslist();
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                ++loadPage;
//                mModel.getBooks(type, titleName, loadPage);
                mRefreshLayout.finishLoadmore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                loadPage = 1;
//                mModel.getBooks(type, titleName, 1);
                mPresenter.newslist();
            }
        });

//        mRefreshLayout.autoRefresh();
        loadinglayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mPresenter.newslist();
            }
        });

    }

    @Override
    public void setTitleBar(ActionBarRes mActionBarRes) {
        super.setTitleBar(mActionBarRes);
        mActionBarRes.mTitle ="新闻列表";
    }

    @Override
    public void newlistSuccess(NewsListBean bean) {

        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        loadinglayout.setStatus(LoadingLayout.Success);

        if(bean !=null && bean.getFemale().size()>0) {
            datas.addAll(bean.getFemale());
        }else{
            loadinglayout.setStatus(LoadingLayout.Empty);
        }
        if(newsListAdapter == null) {
            newsListAdapter = new NewsListAdapter(datas);
            rvClassify.setLayoutManager(new LinearLayoutManager(mContext));
            newsListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            rvClassify.setAdapter(newsListAdapter);
            newsListAdapter.setOnItemClickListener((adapter, view, position) -> {
//                Bundle bundle = new Bundle();
//                bundle.putString("getder", getder);
//                bundle.putString("titleName", mClassifyBeans.get(position).getName());
//                startActivity(BookListActivity.class, bundle);
            });
        }else{
            newsListAdapter.setNewData(datas);
            newsListAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void newlistError(String reason) {
        loadinglayout.setEmptyText(reason).setStatus(LoadingLayout.Error);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showLoading() {
        LoadingHelper.getInstance().showLoading(mContext);

    }

    @Override
    public void hideLoading() {
        LoadingHelper.getInstance().hideLoading();
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void neterror() {
        loadinglayout.setStatus(LoadingLayout.No_Network);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
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
