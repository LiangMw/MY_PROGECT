package com.integrate.mingweidev.mvp.view.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.integrate.mingweidev.R;


/**
 * Created by 梁明伟 on 2018/10/23.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class FunctionAdapter extends BaseAdapter {

    private Activity activity;
    private int[] icons;
    private String[] names;

    public FunctionAdapter(Activity activity, int[] icons, String[] names){
        this.activity = activity;
        this.icons = icons;
        this.names = names;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(activity, R.layout.item_function,null);
            viewHolder.iv_icon = view.findViewById(R.id.iv_icon);
            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_name.setText(names[i]);
        viewHolder.iv_icon.setImageResource(icons[i]);
        return view;
    }

    class ViewHolder{
        private ImageView iv_icon;
        private TextView tv_name;
    }

    public void setData(int[] icons,String[] namse){
        this.icons = icons;
        this.names = names;
        notifyDataSetChanged();
    }
}
