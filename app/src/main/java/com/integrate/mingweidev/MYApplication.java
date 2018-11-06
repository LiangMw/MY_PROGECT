package com.integrate.mingweidev;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.allen.library.RxHttpUtils;
import com.allen.library.config.OkHttpConfig;
import com.allen.library.cookie.store.SPCookieStore;
import com.integrate.mingweidev.api.BasicParamsInterceptor;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.SharedPreUtils;
import com.integrate.mingweidev.utils.ThemeUtils;
import com.integrate.mingweidev.widget.CircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.weavey.loading.lib.LoadingLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;


/**
 * Created by Liang_Lu on 2017/11/21.
 */

public class MYApplication extends Application {
    public static PackageInfo packageInfo;
    private static ArrayList<Activity> allActivity = new ArrayList<>();
    private static MYApplication app;
    private Map<String, String> baseparam = new HashMap<>();

    public static Context getAppContext() {
        return app;
    }

    public static Resources getAppResources() {
        return app.getResources();
    }

    /**
     * 添加activity同意管理
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (!allActivity.contains(activity)) {
            allActivity.add(activity);
        }
    }

    public static void removeAllActivity() {
        for (Activity a : allActivity) {
            a.finish();
        }
    }

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        app = this;
        try {
            packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initRxHttpUtils();
//        startService(new Intent(getAppContext(), BookDownloadService.class));
        initRefresh();
        initLoadingLayout();
    }

    private void initRxHttpUtils() {
        Map<String, String> baseparam = new HashMap<>();
        baseparam.put("guid", "454654564564465");
        baseparam.put("token", "fjaksjfkjasljf");
        /**
         * 初始化配置
         */
        OkHttpClient okHttpClient = new OkHttpConfig
                .Builder(this)
                //全局的请求头信息
                .setHeaders(tokenMap())
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
                .setCache(true)
                //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
                //不设置的话，默认不对cookie做处理
                .setCookieType(new SPCookieStore(this))
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                .setAddInterceptor(new BasicParamsInterceptor.Builder().addQueryParamsMap(baseparam).build())
//                .setAddInterceptor(new TokenInterceptord())
                //全局ssl证书认证
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                //.setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setDebug(true)
                .build();

        RxHttpUtils
                .getInstance()
                .init(this)
                .config()
                //配置全局baseUrl
                .setBaseUrl(Constant.BASE_URL)
                //开启全局配置
                .setOkClient(okHttpClient);

    }

    private void initRefresh() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            CircleHeader header = new CircleHeader(context);
            layout.setPrimaryColorsId(ThemeUtils.getThemeColorId(), R.color.white);
            return header;
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Translate));
    }

    private void initLoadingLayout() {
        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.drawable.ic_error_icon)
                .setEmptyImage(R.drawable.ic_empty_error)
                .setNoNetworkImage(R.drawable.ic_net_error)
                .setAllTipTextColor(R.color.black)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.black)
                .setReloadButtonWidthAndHeight(150, 40);
    }
}
