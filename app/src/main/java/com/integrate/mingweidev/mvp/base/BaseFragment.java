package com.integrate.mingweidev.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.utils.ToastUtils;

import butterknife.ButterKnife;


/**
 * Created by Liang_Lu on 2017/9/29.
 * Fragment基类
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected Context mContext;
    public View mRootView;
    public T mPresenter;

    private ActionBarRes mActionBarRes = new ActionBarRes();
    private BaseFragmentActivity subActivity;

    /**
     * 获得全局的，防止使用getActivity()为空
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        if(getActivity() instanceof BaseFragmentActivity) {
            subActivity  = (BaseFragmentActivity) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {

        if (mRootView == null) {
            if (getLayoutRes() != 0) {
                mRootView = LayoutInflater.from(mContext)
                        .inflate(getLayoutRes(), container, false);
                ButterKnife.bind(this, mRootView);
                createPresenter();
                initView();
            } else {
                throw new RuntimeException("layoutResID==-1 have u create your layout?");
            }
        }

        return mRootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        setTitleBar(mActionBarRes);
        changeStyle();
    }

    /**
     * 创建presenter实例
     */
    public abstract void createPresenter();


    /**
     * 初始化
     */
    protected abstract void initView();


    /**
     * 吐司
     *
     * @param text
     */
    public void showToast(String text) {
        ToastUtils.show(text);
    }

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

    /**
     * 传入布局文件
     *
     * @return
     */
    public abstract int getLayoutRes();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();//页面销毁网络请求也取消
        }
    }

    public void setTitleBar(ActionBarRes mActionBarRes) {
        this.mActionBarRes = mActionBarRes;
    }

    public void changeStyle() {
        setTitle(mActionBarRes.mTitle);
        setBackIcon(mActionBarRes.leftPic);
        setRightIcon(mActionBarRes.rightPic);
        setRightVisible(mActionBarRes.rightVisible);
        if(subActivity != null) {
            subActivity.initThemeToolBar("789");

        }
    }

    public void setTitle(String title) {
        if (subActivity != null) {
            subActivity.setTitle(title);
        }
    }

    public void setRightVisible(boolean visible) {
        if (subActivity != null) {
            subActivity.setRightVisable(visible);
        }
    }

    public void setBackIcon(int icon) {
        if (subActivity != null) {
            subActivity.setBackicon(icon);
        }
    }

    public void setRightIcon(int icon) {
        if (subActivity != null) {
            subActivity.setRighticon(icon);

        }
    }

    /**
     * 手机物理回退键
     */
    public void onBackPressed() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void initListener() {
    }

    public void onBackListener() {

    }

    public void onRightListener() {

    }

    /**
     * 标题头
     */
    public class ActionBarRes {
        public String mTitle;
        public int rightPic;
        public int leftPic = R.drawable.ic_arrow_back_white_24dp;
        public boolean rightVisible = false;
        public boolean leftVisible = true;
        public boolean titleBarVisible = true;
    }

}
