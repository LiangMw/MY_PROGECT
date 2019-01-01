package com.integrate.mingweidev.mvp.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.integrate.mingweidev.MYApplication;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.utils.SharedPreUtils;
import com.integrate.mingweidev.widget.theme.Theme;

import butterknife.ButterKnife;

/**
 * Created by Liang_Lu on 2017/12/21.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public Context mContext;
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onPreCreate();
        super.onCreate(savedInstanceState);//子类的super.OnCreate必须在setContentView后调用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        mContext = this;
        MYApplication.addActivity(this);
        if (setContentViewId() != 0) {
            setContentView(setContentViewId());
        } else {
            throw new RuntimeException("layoutResID==-1 have u create your layout?");
        }
        createPresenter();
        ButterKnife.bind(this);
        initView();

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 初始化方法
     */
    protected void initView() {

    }

    /**
     * 获取contentView 资源id
     */
    public abstract int setContentViewId();

    /**
     * 创建presenter实例
     */
    public abstract void createPresenter();

    /**
     * activity跳转（无参数）
     *
     * @param className
     */
    public void startActivity(Class<?> className) {
        Intent intent = new Intent(mContext, className);
        startActivity(intent);
    }

    /**
     * activity跳转（有参数）
     *
     * @param className
     */
    public void startActivity(Class<?> className, Bundle bundle) {
        Intent intent = new Intent(mContext, className);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();//页面销毁 网络请求同销毁
        }
    }

    private void onPreCreate() {
        Theme theme = SharedPreUtils.getInstance().getCurrentTheme();
        switch (theme) {
            case Blue:
                setTheme(R.style.BlueTheme);
                break;
            case Red:
                setTheme(R.style.RedTheme);
                break;
            case Brown:
                setTheme(R.style.BrownTheme);
                break;
            case Green:
                setTheme(R.style.GreenTheme);
                break;
            case Purple:
                setTheme(R.style.PurpleTheme);
                break;
            case Teal:
                setTheme(R.style.TealTheme);
                break;
            case Pink:
                setTheme(R.style.PinkTheme);
                break;
            case DeepPurple:
                setTheme(R.style.DeepPurpleTheme);
                break;
            case Orange:
                setTheme(R.style.OrangeTheme);
                break;
            case Indigo:
                setTheme(R.style.IndigoTheme);
                break;
            case LightGreen:
                setTheme(R.style.LightGreenTheme);
                break;
            case Lime:
                setTheme(R.style.LimeTheme);
                break;
            case DeepOrange:
                setTheme(R.style.DeepOrangeTheme);
                break;
            case Cyan:
                setTheme(R.style.CyanTheme);
                break;
            case BlueGrey:
                setTheme(R.style.BlueGreyTheme);
                break;
        }

    }
}
