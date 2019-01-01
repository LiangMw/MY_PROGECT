package com.integrate.mingweidev.mvp.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.integrate.mingweidev.MYApplication;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseActivity;
import com.integrate.mingweidev.mvp.bean.TokenBean;
import com.integrate.mingweidev.mvp.contract.CLogin;
import com.integrate.mingweidev.mvp.model.login.MLogin;
import com.integrate.mingweidev.mvp.presenter.login.PLogin;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.LoadingHelper;
import com.integrate.mingweidev.utils.SharedPreUtils;
import com.integrate.mingweidev.utils.SystemUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.widget.MarqueTextView;
import com.integrate.mingweidev.widget.theme.ColorRelativeLayout;
import com.integrate.mingweidev.widget.theme.ColorTextView;
import com.integrate.mingweidev.widget.theme.ColorView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梁明伟 on 2018/12/27.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class LoginActivity extends BaseActivity<PLogin> implements CLogin.IVLogin<TokenBean> {

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
    @BindView(R.id.actv_username)
    AutoCompleteTextView actvUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.ctv_register)
    ColorTextView ctvRegister;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MYApplication.addActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            statusBar.setVisibility(View.VISIBLE);
            statusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
            statusBar.setLayoutParams(statusBar.getLayoutParams());
        } else {
            statusBar.setVisibility(View.GONE);
        }
        tvToolbarTitle.setText("用户登录");
        ivToolbarMore.setVisibility(View.GONE);

    }

    /**
     * 获取contentView 资源id
     */
    @Override
    public int setContentViewId() {
        return R.layout.activity_login;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {

        mPresenter = new PLogin(this, this, new MLogin());

    }

    @OnClick({R.id.ctv_register, R.id.fab, R.id.iv_toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ctv_register:
                //注册

                break;
            case R.id.fab:
                //登录
                String username = actvUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    ToastUtils.show("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.show("密码不能为空");
                    return;
                }
                mPresenter.login(username, password);
                break;
            case R.id.iv_toolbar_back:
                MYApplication.removeAllActivity();
                break;
        }
    }


    @Override
    public void success(TokenBean tokenBean) {
        if (Constant.SUCCESSCODE.equals(tokenBean.getCode())) {
            SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_GUID);
            SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_TOKEN);
            Constant.TOKEN = tokenBean.getResult().getToken();
            Constant.GUID = tokenBean.getResult().getGuid();
            SharedPreUtils.getInstance().putString(Constant.TAG_GUID, tokenBean.getResult().getGuid());
            SharedPreUtils.getInstance().putString(Constant.TAG_TOKEN, tokenBean.getResult().getToken());
            startActivity(MainActivity.class);
            finish();
            LoadingHelper.getInstance().hideLoading();
        } else {
            ToastUtils.show(tokenBean.getMsg());
            LoadingHelper.getInstance().hideLoading();
        }
    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void showLoading() {
        LoadingHelper.getInstance().showLoading(this);

    }

    @Override
    public void hideLoading() {
        LoadingHelper.getInstance().hideLoading();
    }

    @Override
    public void neterror() {

    }
}
