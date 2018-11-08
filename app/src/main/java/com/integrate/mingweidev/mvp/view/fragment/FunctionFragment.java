package com.integrate.mingweidev.mvp.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.view.adapter.FunctionAdapter;
import com.integrate.mingweidev.utils.AppMethod;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.LoadingHelper;
import com.integrate.mingweidev.utils.LogUtils;

import butterknife.BindView;

/**
 * Created by 梁明伟 on 2017/11/28.
 * 功能首页
 */

public class FunctionFragment extends BaseFragment {

    @BindView(R.id.gv_view)
    GridView gvView;

    private int[] icons = new int[]{R.mipmap.f_files, R.mipmap.f_music, R.drawable.f_photo};
    private String[] names = new String[]{"文件", "音乐", "新闻"};
    private FunctionAdapter adapter;
    private int CHOOSEFILE_CODE = 10001;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                LoadingHelper.getInstance().hideLoading();
            }
        }
    };

    public static FunctionFragment newInstance() {
        FunctionFragment fragment = new FunctionFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_function;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {

    }

    @Override
    public void initView() {
        if (adapter == null) {
            adapter = new FunctionAdapter(getActivity(), icons, names);
            gvView.setAdapter(adapter);
        } else {
            adapter.setData(icons, names);
        }
        gvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        AppMethod.postShow((Activity) mContext, FragmentPages.WINDCAR_TestFragment);
                        break;
                    case 1:

                        break;
                    case 2:
                        AppMethod.postShow((Activity) mContext, FragmentPages.NEWS_LIST);
                        break;
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSEFILE_CODE) {
            if (data != null) {
                String dat = data.getStringExtra(Constant.TAG_FILERESULT);
                LogUtils.e("----------dat:" + dat);
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
