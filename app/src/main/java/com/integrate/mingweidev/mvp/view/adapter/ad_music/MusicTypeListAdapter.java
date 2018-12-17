package com.integrate.mingweidev.mvp.view.adapter.ad_music;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.mvp.bean.SheetInfo;
import com.integrate.mingweidev.mvp.model.music.MMusicImpl;
import com.integrate.mingweidev.mvp.view.fragment.FragmentPages;
import com.integrate.mingweidev.utils.AppMethod;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;
import com.integrate.mingweidev.utils.rxhelper.RxObservable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Liang_Lu on 2017/12/4.
 */

public class MusicTypeListAdapter extends BaseMultiItemQuickAdapter<SheetInfo,BaseViewHolder> {

    private Activity mActivity;

    public MusicTypeListAdapter(Activity activity, List data) {
        super(data);
        addItemType(Constant.TYPE_PROFILE, R.layout.view_holder_sheet_profile);
        addItemType(Constant.TYPE_MUSIC_LIST, R.layout.view_holder_sheet);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, SheetInfo item) {
        switch (item.getItemType()) {
            case 0:
                helper.setText(R.id.tv_profile,item.getTitle());
                break;
            case 1:
                Map<String,String> param = new HashMap<>();
                param.put(Constant.PARAM_TYPE,item.getType());
                param.put(Constant.PARAM_OFFSET,"0");
                param.put(Constant.PARAM_METHOD,Constant.METHOD_GET_MUSIC_LIST);
                param.put(Constant.PARAM_SIZE,"3");
                new MMusicImpl().getSongList(new RxObservable<OnlineMusicList>() {
                    @Override
                    public void onSuccess(OnlineMusicList list) {
                        List<OnlineMusicList.SongListBean> songlist = list.getSong_list();
                        if(songlist!=null && songlist.size()>0) {
                            int size = songlist.size();
                            if(size>0) {
                                helper.setText(R.id.tv_music_1, mActivity.getString(R.string.song_list_item_title_1,songlist.get(0).getTitle(),songlist.get(0).getArtist_name()));
                            }
                            if(size>1) {
                                helper.setText(R.id.tv_music_2, mActivity.getString(R.string.song_list_item_title_1,songlist.get(1).getTitle(),songlist.get(1).getArtist_name()));
                            }
                            if(size>2) {
                                helper.setText(R.id.tv_music_3, mActivity.getString(R.string.song_list_item_title_1,songlist.get(2).getTitle(),songlist.get(2).getArtist_name()));
                            }
                            if(!TextUtils.isEmpty(list.getBillboard().getPic_s260())) {
                                ImageLoadManage.getInstance().display(mActivity,helper.getView(R.id.iv_cover),list.getBillboard().getPic_s260());
                            }else{
                                helper.setImageResource(R.id.iv_cover, R.mipmap.default_cover);
                            }
                        }

                    }

                    @Override
                    public void onFail(String reason) {

                    }
                },param);

                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.MUSIC_LIST_TYPE, item);
                        AppMethod.postShow(mActivity, FragmentPages.SONGS_LIST,bundle);
                    }
                });
                break;
        }
    }

}
