package com.integrate.mingweidev.api;

import com.allen.library.RxHttpUtils;
import com.allen.library.cookie.store.MemoryCookieStore;
import com.allen.library.http.SingleRxHttp;
import com.integrate.mingweidev.utils.SharedPreUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * Created by Liang_Lu on 2017/8/31.
 */

public class DevMvpApi {

    private static Retrofit mRetrofit;

    /**
     * Retrofit初始化
     * @return
     */
 /*  public static Retrofit createApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().
                connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);

        httpClientBuilder.addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY));
        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Url.BASE_URL)
                .build();
        return mRetrofit;

    }*/

    /**
     * 获取token
     *
     * @return
     */
    public static Map tokenMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("access-token", SharedPreUtils.getInstance().getString("token", "weyue"));
        map.put("app-type", "Android");
//        map.put("version-code", WYApplication.packageInfo.versionCode);
        return map;
    }

    /**
     * 创建单独的请求
     * @return
     */
    public static SingleRxHttp createApi(){
//        return RxHttpUtils.getSInstance().addHeaders(tokenMap());
//        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(BookService.class)
//                .bookChapters(bookId)
//                .compose(Transformer.switchSchedulers())
//                .subscribe(new RxObserver<BookChaptersBean>() {
//                    @Override
//                    protected void onError(String errorMsg) {
//
//                    }
//
//                    @Override
//                    protected void onSuccess(BookChaptersBean data) {
//                        if (iBookChapters != null) {
//                            iBookChapters.bookChapters(data);
//                        }
//                    }
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        addDisposadle(d);
//                    }
//                });

       return RxHttpUtils
                .getSInstance()
                .baseUrl("https://api.douban.com/")
//                .addHeaders(headerMaps)
                .cache(true)
                .cachePath("cachePath", 1024 * 1024 * 100)
                .sslSocketFactory()
                .cookieType(new MemoryCookieStore())
                .writeTimeout(10)
                .readTimeout(10)
                .connectTimeout(10)
                .log(true);
//                .createSApi(ApiService.class)
//                .getTop250(10)
//                .compose(Transformer.<Top250Bean>switchSchedulers(loading_dialog))
//                .subscribe(new CommonObserver<Top250Bean>() {
//
//                    @Override
//                    protected void onError(String errorMsg) {
//                        //错误处理
//                    }
//
//                    @Override
//                    protected void onSuccess(Top250Bean top250Bean) {
//                        //业务处理
//                    }
//                });
    }
}
