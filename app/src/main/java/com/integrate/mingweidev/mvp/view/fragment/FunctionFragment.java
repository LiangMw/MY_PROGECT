package com.integrate.mingweidev.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.view.activity.BookActivity;
import com.integrate.mingweidev.mvp.view.adapter.FunctionAdapter;
import com.integrate.mingweidev.utils.AppMethod;
import com.integrate.mingweidev.utils.ToastUtils;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 梁明伟 on 2017/11/28.
 * 功能首页
 */

public class FunctionFragment extends BaseFragment {

    @BindView(R.id.gv_view)
    GridView gvView;
    Unbinder unbinder;

    private int[] icons = new int[]{R.mipmap.f_files,R.mipmap.f_music,R.drawable.f_photo};
    private String[] names = new String[]{"文件","音乐","图片"};
    private FunctionAdapter adapter;

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
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {

    }

    @Override
    public void initView() {
        if(adapter == null) {
            adapter = new FunctionAdapter(getActivity(),icons,names);
            gvView.setAdapter(adapter);
        }else {
            adapter.setData(icons,names);
        }

        gvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case  0:
                        ToastUtils.show(names[i]);
                        AppMethod.postShow(getActivity(),FragmentPages.WINDCAR_IDENTITYCHOICE);
//                        Intent intent = new Intent(getActivity(), BaseFragmentActivity.class);
////                    intent.putExtra(Constant.CONTENT_KEY, page.getValue());
////                    intent.putExtra(Constant.DATA_KEY, data);
//                        startActivity(intent);
                       /* RxPermissions rxPermissions = new RxPermissions(getActivity());
                        rxPermissions
                                .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(permission -> {
                                    if (permission.granted) {
                                        // 用户已经同意该权限.
                                        AppMethod.postShow((Activity) mContext,FragmentPages.WINDCAR_IDENTITYCHOIC);

//                                        Intent intent = new Intent(getActivity(), BasesActivity.class);
//                                        intent.putExtra(Constant.CONTENT_KEY, FragmentPages.getPageByValue(1001));
//                                        getActivity().startActivity(intent);

                                    } else if (permission.shouldShowRequestPermissionRationale) {
                                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                        ToastUtils.show("用户拒绝开启读写权限");
                                    } else {
                                        // 用户拒绝了该权限，并且选中『不再询问』
//                                        mResideLayout.closePane();
                                        SnackBarUtils.makeShort(getActivity().getWindow().getDecorView(), "读写权限被禁止,移步到应用管理允许权限").show("去设置", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                BaseUtils.getAppDetailSettingIntent(mContext, getActivity().getPackageName());
                                            }
                                        });
                                    }
                                });*/

                        break;

                    case 1:
                        startActivity(BookActivity.class);
                        ToastUtils.show(names[i]);
                        break;
                }
                ToastUtils.show(names[i]);
            }
        });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
