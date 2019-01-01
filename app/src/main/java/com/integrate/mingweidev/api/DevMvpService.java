package com.integrate.mingweidev.api;


import com.integrate.mingweidev.mvp.base.BaseResponse;
import com.integrate.mingweidev.mvp.bean.BannerBean;
import com.integrate.mingweidev.mvp.bean.BookBean;
import com.integrate.mingweidev.mvp.bean.NewsListBean;
import com.integrate.mingweidev.mvp.bean.OnlineMusicList;
import com.integrate.mingweidev.mvp.bean.TokenBean;
import com.integrate.mingweidev.mvp.bean.UserInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Liang_Lu on 2017/9/1.
 */

public interface DevMvpService {

    @GET(Url.LOGIN)
    Observable<TokenBean> login(@Query("tel") String tel, @Query("pwd") String pwd);

    @GET(Url.REGISTER)
    Observable<BaseResponse> register(@Query("tel") String tel, @Query("pwd") String pwd);

    @GET(Url.USERINFO)
    Observable<UserInfoBean> userinfo();

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

    //获取歌曲类型列表
    @GET("restserver/ting/")
    Observable<OnlineMusicList> songList(@QueryMap Map<String, String> map);

}
