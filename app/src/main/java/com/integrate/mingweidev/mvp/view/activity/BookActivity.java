package com.integrate.mingweidev.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseActivity;
import com.integrate.mingweidev.mvp.bean.BookBean;
import com.integrate.mingweidev.mvp.contract.CBook;
import com.integrate.mingweidev.mvp.presenter.PBookImpl;

/**
 * Created by Liang_Lu on 2017/12/21.
 * V层 用于数据和页面UI展示（Fragment Dialog 同理）
 */

public class BookActivity extends BaseActivity<PBookImpl> implements CBook.IVBook {

    private TextView mTv;
    private TextView tv_path;
    private Button mBtn;

    @Override
    protected void initView() {
        super.initView();
        mBtn = findViewById(R.id.btn);
        mTv = findViewById(R.id.tv);
        tv_path = findViewById(R.id.tv_path);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.pBook();
            }
        });
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_book;
    }

    @Override
    public void createPresenter() {
        mPresenter = new PBookImpl(mContext, this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void neterror() {

    }

    public void setProsess(long l, long l1, float v, boolean b, String s){

        mTv.setText("下 载中：" + v + "%");
        if (b) {
            tv_path.setText("下载文件路径：" + s);
        }

    }

    @Override
    public void vBookSuccess(BookBean bean) {
        mTv.setText("网络请求成功"+bean.getFemale().get(0).getName());
    }

    @Override
    public void vBookError(String reason) {
        mTv.setText(reason);
    }

}
