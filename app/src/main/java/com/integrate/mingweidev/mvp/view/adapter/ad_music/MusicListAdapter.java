package com.integrate.mingweidev.mvp.view.adapter.ad_music;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.integrate.mingweidev.R;

import java.util.List;


/**
 * Created by Liang_Lu on 2017/12/4.
 */

public class MusicListAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    int i = 1;
    private List<MultipleItem> data;

    public MusicListAdapter(List<MultipleItem> data) {
        super(data);
        this.data = data;
        addItemType(MultipleItem.TYPE_PROFILE, R.layout.view_holder_sheet_profile);
        addItemType(MultipleItem.TYPE_MUSIC_LIST, R.layout.view_holder_sheet);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE_PROFILE:
                helper.setText(R.id.tv_profile,item.getData().getTitle());
                break;
            case MultipleItem.TYPE_MUSIC_LIST:
                helper.setText(R.id.tv_music_1, "1.加载中…");
                helper.setText(R.id.tv_music_2, "2.加载中…");
                helper.setText(R.id.tv_music_3, "3.加载中…");
                helper.setImageResource(R.id.iv_cover, R.mipmap.default_cover);
                break;
        }
    }
}
