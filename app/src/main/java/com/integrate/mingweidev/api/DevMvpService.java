package com.integrate.mingweidev.api;


import com.integrate.mingweidev.mvp.bean.BannerBean;
import com.integrate.mingweidev.mvp.bean.BookBean;
import com.integrate.mingweidev.mvp.bean.NewsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Liang_Lu on 2017/9/1.
 */

public interface DevMvpService {

    @GET(Url.BOOK_CLASSIFY + "/{bookId}")
    Observable<BookBean> bookClassify(@Path("bookId") String bookId);

    @GET(Url.BOOK_CLASSIFYFRAGMENT)
    Observable<BookBean> bookClassifyfragment(@Query("name") String name, @Query("ages") int age);

    //获取新闻列表
    @GET(Url.NEWS_LIST)
    Observable<NewsListBean> newsList(@Query("newsid") String newsid);

    //获取轮播图
    @GET("music/getbanners/")
    Observable<BannerBean> bannerList();

}
