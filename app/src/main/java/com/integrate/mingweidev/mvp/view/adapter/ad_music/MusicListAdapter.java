package com.integrate.mingweidev.mvp.view.adapter.ad_music;

import android.app.Activity;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.mvp.bean.SheetInfo;
import com.integrate.mingweidev.mvp.model.music.MMusicImpl;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Liang_Lu on 2017/12/4.
 */

public class MusicListAdapter extends BaseMultiItemQuickAdapter<SheetInfo,BaseViewHolder> {

    private Activity mActivity;

    public MusicListAdapter(Activity activity,List data) {
        super(data);
        addItemType(0, R.layout.view_holder_sheet_profile);
        addItemType(1, R.layout.view_holder_sheet);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, SheetInfo item) {
        switch (item.getType()) {
            case 0:
                helper.setText(R.id.tv_profile,item.getTitle());
                break;
            case 1:
                Map<String,String> param = new HashMap<>();
                param.put(Constant.PARAM_TYPE,item.getType()+"");
                param.put(Constant.PARAM_OFFSET,"0");
                param.put(Constant.PARAM_METHOD,Constant.METHOD_GET_MUSIC_LIST);
                param.put(Constant.PARAM_SIZE,"3");
                new MMusicImpl().getSongList(new RxObservable<OnlineMusicList>() {
                    @Override
                    public void onSuccess(OnlineMusicList list) {
                        helper.setText(R.id.tv_music_1, list.getSong_list().get(0).getTitle());
                        helper.setText(R.id.tv_music_2, list.getSong_list().get(1).getTitle());
                        helper.setText(R.id.tv_music_3, list.getSong_list().get(2).getTitle());
                        helper.setImageResource(R.id.iv_cover, R.mipmap.default_cover);
                        ImageLoadManage.getInstance().display(mActivity,helper.getView(R.id.iv_cover),list.getSong_list().get(0).getPic_small());

                    }

                    @Override
                    public void onFail(String reason) {

                    }
                },param);
                break;
        }
    }

}
