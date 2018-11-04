package com.integrate.mingweidev.mvp.view.adapter.news;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.NewsListBean;
import com.integrate.mingweidev.utils.LogUtils;

import java.util.List;


/**
 * Created by Liang_Lu on 2017/12/4.
 */

public class NewsListAdapter extends BaseQuickAdapter<NewsListBean.FemaleBean, BaseViewHolder> {

    private  List<NewsListBean.FemaleBean> data;
    int i = 1;

    public NewsListAdapter(@Nullable List<NewsListBean.FemaleBean> data) {
        super(R.layout.item_newslist, data);
        this.data =data;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsListBean.FemaleBean item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_count, item.getBookCount() + "本");

        LogUtils.e("------------------"+i++);

//        Glide.with(mContext).load(Constant.BASE_URL+item.getIcon())
//                .apply(new RequestOptions().placeholder(R.drawable.ic_default))
//                .into((ImageView) helper.getView(R.id.iv_icon));
    }


}
