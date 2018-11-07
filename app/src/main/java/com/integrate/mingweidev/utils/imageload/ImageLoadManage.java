package com.integrate.mingweidev.utils.imageload;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.integrate.mingweidev.R;

/**
 * Created by 梁明伟 on 2018/11/7.
 * Copyright © 2018年 CETC. All rights reserved.
 * 图片加载
 */
public class ImageLoadManage {

    private volatile static ImageLoadManage imageLoadManage = null;

    private ImageLoadManage() {

    }

    public static ImageLoadManage getInstance() {

        if (imageLoadManage == null) {
            synchronized (ImageLoadManage.class) {
                if (imageLoadManage == null) {
                    imageLoadManage = new ImageLoadManage();
                }
            }

        }
        return imageLoadManage;
    }

    /**
     * 不带圆角使用默认 placehoder
     *
     * @param context
     * @param imageView
     * @param url
     */
    public void display(Context context, ImageView imageView, String url) {
        display(context, imageView, url, -1, -1);
    }

    /**
     * 带圆角使用默认 placehoder
     *
     * @param context
     * @param imageView
     * @param url
     */
    public void display(Context context, ImageView imageView, String url, boolean isround) {
        display(context, imageView, url, -1, -1, isround);
    }

    /**
     * 所有属性都带有
     *
     * @param context
     * @param imageView 显示的imageView
     * @param url       网络图片地址
     * @param processid 加载中显示的图片
     * @param errorid   加载出错显示的图片
     * @param isround   是否圆角
     */
    public void display(Context context, ImageView imageView, String url, int processid, int errorid, boolean isround) {

        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.NONE);
        options.skipMemoryCache(true);
        options.placeholder(processid != -1 ? processid : errorid != -1 ? errorid : R.drawable.icon_placehoder);
        if (isround) {
            options.transform(new CircleCrop());
        }

        Glide.with(context).load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 不带圆角
     *
     * @param context
     * @param imageView
     * @param url
     * @param processid
     * @param errorid
     */
    public void display(Context context, ImageView imageView, String url, int processid, int errorid) {
        display(context, imageView, url, processid, errorid);
    }


    /**
     * 所有属性都带有
     *
     * @param context
     * @param imageView      显示的imageView
     * @param url            网络图片地址
     * @param transformation 自定义样式
     */
    public void display(Context context, ImageView imageView, String url, Transformation transformation) {
        display(context, imageView, url, -1, -1, transformation);
    }


    /**
     * 所有属性都带有
     *
     * @param context
     * @param imageView      显示的imageView
     * @param url            网络图片地址
     * @param processid      加载中显示的图片
     * @param errorid        加载出错显示的图片
     * @param transformation 自定义样式
     */
    public void display(Context context, ImageView imageView, String url, int processid, int errorid, Transformation transformation) {

        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.NONE);
        options.skipMemoryCache(true);
        options.placeholder(processid != -1 ? processid : errorid != -1 ? errorid : R.drawable.icon_placehoder);
        options.transform(transformation);
        Glide.with(context).load(url)
                .apply(options)
                .into(imageView);
    }

}
