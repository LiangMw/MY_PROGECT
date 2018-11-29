package com.integrate.mingweidev.mvp.view.adapter.ad_music;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.SheetInfo;

import java.util.List;


/**
 * Created by Liang_Lu on 2017/12/4.
 */

public class MusicListAdapter extends BaseMultiItemQuickAdapter<SheetInfo,BaseViewHolder> {


    public MusicListAdapter(List data) {
        super(data);
        addItemType(0, R.layout.view_holder_sheet_profile);
        addItemType(1, R.layout.view_holder_sheet);
    }

    @Override
    protected void convert(BaseViewHolder helper, SheetInfo item) {
        switch (item.getType()) {
            case 0:
                helper.setText(R.id.tv_profile,item.getTitle());
                break;
            case 1:
                helper.setText(R.id.tv_music_1, "1.加载中…");
                helper.setText(R.id.tv_music_2, "2.加载中…");
                helper.setText(R.id.tv_music_3, "3.加载中…");
                helper.setImageResource(R.id.iv_cover, R.mipmap.default_cover);
                break;
        }
    }

}
