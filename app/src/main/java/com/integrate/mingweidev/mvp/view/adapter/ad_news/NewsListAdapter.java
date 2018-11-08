package com.integrate.mingweidev.mvp.view.adapter.ad_news;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.NewsListBean;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;

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
        ImageLoadManage.getInstance().display(mContext,(ImageView)helper.getView(R.id.iv_icon),"http://mallcomment.holdsoft.cn/1510224399351",-1,-1);

//        Glide.with(mContext).load("http://mallcomment.holdsoft.cn/1510224399351")
//                .apply(new RequestOptions().placeholder(R.drawable.ic_default))
//                .into((ImageView) helper.getView(R.id.iv_icon));
    }


}
