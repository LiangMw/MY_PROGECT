package com.integrate.mingweidev.mvp.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.view.activity.BookActivity;
import com.integrate.mingweidev.mvp.view.adapter.FunctionAdapter;
import com.integrate.mingweidev.utils.AppMethod;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.LogUtils;
import com.integrate.mingweidev.utils.SnackBarUtils;
import com.integrate.mingweidev.utils.ThemeUtils;
import com.integrate.mingweidev.utils.ToastUtils;

import butterknife.BindView;
import butterknife.Unbinder;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

/**
 * Created by 梁明伟 on 2017/11/28.
 * 功能首页
 */

public class FunctionFragment extends BaseFragment {

    @BindView(R.id.gv_view)
    GridView gvView;
    Unbinder unbinder;

    private int[] icons = new int[]{R.mipmap.f_files, R.mipmap.f_music, R.drawable.f_photo};
    private String[] names = new String[]{"文件", "音乐", "图片"};
    private FunctionAdapter adapter;
    private int CHOOSEFILE_CODE = 10001;

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
                        if (!HiPermission.checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            HiPermission.create(getActivity())
                                    .title("请允许以下权限")
                                    .msg("为了收复钓鱼岛")
                                    .style(R.style.AppTheme)
                                    .filterColor(ThemeUtils.getThemeColor())
                                    .animStyle(R.anim.popup_enter)
                                    .checkSinglePermission(Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionCallback() {
                                        @Override
                                        public void onClose() {
                                            SnackBarUtils.makeShort(getActivity().getWindow().getDecorView(), "读写权限被禁止");
                                        }

                                        @Override
                                        public void onFinish() {

                                        }

                                        @Override
                                        public void onDeny(String permission, int position) {

                                        }

                                        @Override
                                        public void onGuarantee(String permission, int position) {
                                            AppMethod.postShowForResult(FunctionFragment.this, CHOOSEFILE_CODE, FragmentPages.WINDCAR_IDENTITYCHOICE);
                                        }
                                    });
                        } else {
                            AppMethod.postShowForResult(FunctionFragment.this, CHOOSEFILE_CODE, FragmentPages.WINDCAR_IDENTITYCHOICE);
                        }
                        break;
                    case 1:
                        startActivity(BookActivity.class);
                        ToastUtils.show(names[i]);
                        break;
                    case 2:
                        AppMethod.postShow((Activity) mContext, FragmentPages.WINDCAR_TestFragment);
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
        unbinder.unbind();
    }
}
