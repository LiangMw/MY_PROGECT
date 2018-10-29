package com.integrate.mingweidev.mvp.base;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.view.fragment.FragmentPages;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.DimenUtils;
import com.integrate.mingweidev.utils.SharedPreUtils;
import com.integrate.mingweidev.utils.SystemUtils;
import com.integrate.mingweidev.widget.MarqueTextView;
import com.integrate.mingweidev.widget.theme.ColorRelativeLayout;
import com.integrate.mingweidev.widget.theme.ColorView;
import com.integrate.mingweidev.widget.theme.Theme;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 梁明伟 on 2018/10/24.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class BaseFragmentActivity extends AppCompatActivity {

    @BindView(R.id.status_bar)
    ColorView statusBar;
    @BindView(R.id.iv_toolbar_back)
    AppCompatImageView ivToolbarBack;
    @BindView(R.id.tv_toolbar_title)
    MarqueTextView tvToolbarTitle;
    @BindView(R.id.iv_toolbar_more)
    AppCompatImageView ivToolbarMore;
    @BindView(R.id.crl)
    ColorRelativeLayout crl;
    @BindView(R.id.container)
    FrameLayout container;

    public BaseFragment currFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        int value = getIntent().getExtras().getInt(Constant.CONTENT_KEY, -1);
        if (value != -1) {
            try {
                try {
                    currFragment = (BaseFragment) FragmentPages
                            .getPageByValue(value).newInstance();
                    changeFragment(R.id.container, currFragment);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        initThemeToolBar("");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    private void changeFragment(int resView, BaseFragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
            transaction.addToBackStack(targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
        }
        transaction.commit();
    }

    /**
     * 自定义titlebar 默认带返回按钮
     *
     * @param title
     */
    public void initThemeToolBar(String title) {
        initStatusBar();
        AppCompatImageView mIvToolbarMore = findViewById(R.id.iv_toolbar_more);
        AppCompatImageView mIvToolbarBack = findViewById(R.id.iv_toolbar_back);
        TextView mTvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        mTvToolbarTitle.setSelected(true);
        mIvToolbarBack.setImageResource(R.drawable.ic_arrow_back_white_24dp);
//        setIconDrawable(mTvToolbarTitle, R.drawable.ic_arrow_back_white_24dp);
        mIvToolbarMore.setVisibility(View.GONE);
        mTvToolbarTitle.setText(title);
        mIvToolbarBack.setOnClickListener(v -> {
            finish();
        });
        onPreCreate();
    }

    public void initStatusBar() {
        statusBar = findViewById(R.id.status_bar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            statusBar.setVisibility(View.VISIBLE);
            statusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
            statusBar.setLayoutParams(statusBar.getLayoutParams());
        } else {
            statusBar.setVisibility(View.GONE);
        }
    }

    public void onPreCreate() {
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

    /**
     * 自定义titlebar 自定义图标
     *
     * @param title
     * @param iconRes
     */
    public void initThemeToolBar(String title, boolean isMoreIcon, @DrawableRes int iconRes, View.OnClickListener clickListener) {
        initStatusBar();
        AppCompatImageView mIvToolbarMore = findViewById(R.id.iv_toolbar_more);
        AppCompatImageView mIvToolbarBack = findViewById(R.id.iv_toolbar_back);
        TextView mTvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        mTvToolbarTitle.setSelected(true);
        if (isMoreIcon) {
            mIvToolbarBack.setImageResource(R.drawable.ic_arrow_back_white_24dp);
//            setIconDrawable(mTvToolbarTitle, R.drawable.ic_arrow_back_white_24dp);
            mIvToolbarMore.setVisibility(View.VISIBLE);
            mTvToolbarTitle.setText(title);
            mIvToolbarBack.setOnClickListener(v -> finish());
            mIvToolbarMore.setOnClickListener(clickListener);
            mIvToolbarMore.setImageResource(iconRes);
        } else {
//            Glide.with(mContext).load(iconRes).into(mIvToolbarBack);
            mIvToolbarBack.setImageResource(iconRes);
            mIvToolbarMore.setVisibility(View.GONE);
            mTvToolbarTitle.setText(title);
            mIvToolbarBack.setOnClickListener(clickListener);
        }
    }

    /**
     * 自定义titlebar 自定义图标
     *
     * @param title
     * @param iconRes
     */
    public void initThemeToolBar(String title, @DrawableRes int iconRes, @DrawableRes int moreRes, View.OnClickListener iconClickListener, View.OnClickListener moreClickListener) {
        initStatusBar();
        AppCompatImageView mIvToolbarMore = findViewById(R.id.iv_toolbar_more);
        AppCompatImageView mIvToolbarBack = findViewById(R.id.iv_toolbar_back);
        TextView mTvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        mTvToolbarTitle.setSelected(true);
        mIvToolbarBack.setImageResource(iconRes);
//        setIconDrawable(mTvToolbarTitle, iconRes);
        mIvToolbarMore.setVisibility(View.VISIBLE);
        mTvToolbarTitle.setText(title);
        mIvToolbarBack.setOnClickListener(iconClickListener);
//        mTvToolbarTitle.setOnClickListener(iconClickListener);
        mIvToolbarMore.setImageResource(moreRes);
        mIvToolbarMore.setOnClickListener(moreClickListener);
//        onPreCreate();
    }

    /**
     * 设置textview图标
     *
     * @param view
     * @param iconRes
     */
    private void setIconDrawable(TextView view, @DrawableRes int iconRes) {
        view.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(iconRes),
                null, null, null);
        view.setCompoundDrawablePadding(DimenUtils.dp2px(10));
    }

    public void setBackicon(int icon) {
        ivToolbarBack.setImageResource(icon);
    }

    public void setTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    public void setRighticon(int icon) {
        ivToolbarMore.setImageResource(icon);

    }

    public void setRightVisable(boolean visable) {
        ivToolbarMore.setVisibility(visable ? View.VISIBLE : View.GONE);
    }

    @OnClick({R.id.iv_toolbar_back, R.id.iv_toolbar_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
//                backListener();
                finish();
                break;
            case R.id.iv_toolbar_more:
                rightListener();
                break;
        }
    }

    public void rightListener() {
        if (currFragment != null) {
            currFragment.onRightListener();
        }
    }

    public void backListener() {
        if (currFragment != null) {
            currFragment.onBackListener();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            currFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
