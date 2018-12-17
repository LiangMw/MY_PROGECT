package com.integrate.mingweidev.mvp.view.adapter.ad_music;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;

import java.util.List;


/**
 * Created by Liang_Lu on 2017/12/4.
 */

public class MusicListAdapter extends BaseQuickAdapter<OnlineMusicList.SongListBean, BaseViewHolder> {

    private  List<OnlineMusicList.SongListBean> data;
    int i = 1;

    public MusicListAdapter(@Nullable List<OnlineMusicList.SongListBean> data) {
        super(R.layout.view_holder_music, data);
        this.data =data;
    }

    @Override
    protected void convert(BaseViewHolder helper, OnlineMusicList.SongListBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        String artist = getArtistAndAlbum(item.getArtist_name(), item.getAlbum_title());
        helper.setText(R.id.tv_artist, artist);
        helper.setVisible(R.id.iv_more,true);
        ImageLoadManage.getInstance().display(mContext,(ImageView)helper.getView(R.id.iv_cover),item.getPic_small(),-1,-1);

    }

    private boolean isShowDivider(int position) {
        return position != data.size() - 1;
    }

    public static String getArtistAndAlbum(String artist, String album) {
        if (TextUtils.isEmpty(artist) && TextUtils.isEmpty(album)) {
            return "";
        } else if (!TextUtils.isEmpty(artist) && TextUtils.isEmpty(album)) {
            return artist;
        } else if (TextUtils.isEmpty(artist) && !TextUtils.isEmpty(album)) {
            return album;
        } else {
            return artist + " - " + album;
        }
    }


}
