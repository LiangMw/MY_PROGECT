package com.integrate.mingweidev.api;


import com.integrate.mingweidev.mvp.bean.BookBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Liang_Lu on 2017/9/1.
 */

public interface DevMvpService {


    @GET(Url.BOOK_CLASSIFY)
    Observable<BookBean> bookClassify();

}
