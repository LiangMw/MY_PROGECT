package com.integrate.mingweidev.mvp.view.fragment.files;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.LocalFileBean;
import com.integrate.mingweidev.mvp.view.adapter.ad_files.LocalFileAdapter;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.FileUtils;
import com.integrate.mingweidev.utils.LoadingHelper;
import com.integrate.mingweidev.utils.LogUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.utils.rxhelper.RxUtils;
import com.integrate.mingweidev.widget.DividerItemDecoration;
import com.integrate.mingweidev.widget.theme.ColorButton;
import com.weavey.loading.lib.LoadingLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.allen.library.RxHttpUtils.addDisposable;

/**
 * Created by Liang_Lu on 2017/11/29.
 */

public class LocalFilesFragment extends BasesFileFragment {

    @BindView(R.id.btn_scan)
    ColorButton mBtnScan;
    @BindView(R.id.rv_files)
    RecyclerView mRvFiles;
    @BindView(R.id.loadlayout)
    LoadingLayout loadlayout;
    @BindView(R.id.tv_searchpath)
    TextView tv_searchpath;
    @BindView(R.id.file_system_cb_selected_all)
    CheckBox mCbSelectAll;
    @BindView(R.id.file_system_btn_add_book)
    Button mBtnAddBook;
    @BindView(R.id.file_system_btn_delete)
    Button mBtnDelete;

    List<LocalFileBean> mFileBeans = new ArrayList<>();

    public static LocalFilesFragment newInstance() {
        LocalFilesFragment fragment = new LocalFilesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_local_book;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {

    }

    @Override
    public void initView() {

        mAdapter = new LocalFileAdapter(mFileBeans);
        mRvFiles.setLayoutManager(new LinearLayoutManager(mContext));
        mRvFiles.addItemDecoration(new DividerItemDecoration(mContext));
        mRvFiles.setAdapter(mAdapter);
        //设置某些按钮的不可点击
        setMenuClickable(false);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            //如果是已加载的文件，则点击事件无效。
//            String id = mFileBeans.get(position).getFile().getAbsolutePath();
//            if (CollBookHelper.getsInstance().findBookById(id) != null) {
//                return;
//            }
            mAdapter.setCheckedItem(position);
            changeMenuStatus();
            //反馈
            if (mListener != null) {
                mListener.onItemCheckedChange(mAdapter.getItemIsChecked(position));
            }
        });
        mBtnScan.setOnClickListener(v -> {
            tv_searchpath.setVisibility(View.VISIBLE);
            mBtnScan.setEnabled(false);
            scanFiles(tv_searchpath);
        });
    }

    @Override
    public void setTitleBar(ActionBarRes mActionBarRes) {
        super.setTitleBar(mActionBarRes);
        mActionBarRes.mTitle = getString(R.string.searchfile);
    }

    @Override
    public void initListener() {
        super.initListener();
        this.setOnFileCheckedListener(new OnFileCheckedListener() {
            @Override
            public void onItemCheckedChange(boolean isChecked) {
                changeMenuStatus();
            }

            @Override
            public void onCategoryChanged() {
                //状态归零
                setCheckedAll(false);
                //改变菜单
                changeMenuStatus();
                //改变是否能够全选
                changeCheckedAllStatus();
            }
        });
    }

    /**
     * 改变全选按钮的状态
     */
    private void changeCheckedAllStatus() {
        //获取可选择的文件数量
        int count = getCheckableCount();

        //设置是否能够全选
        if (count > 0) {
            mCbSelectAll.setClickable(true);
            mCbSelectAll.setEnabled(true);
        } else {
            mCbSelectAll.setClickable(false);
            mCbSelectAll.setEnabled(false);
        }
    }

    private void setMenuClickable(boolean isClickable) {

        //设置是否可删除
        mBtnDelete.setEnabled(isClickable);
        mBtnDelete.setClickable(isClickable);

        //设置是否可添加书籍
        mBtnAddBook.setEnabled(isClickable);
        mBtnAddBook.setClickable(isClickable);
    }

    /**
     * 改变底部选择栏的状态
     */
    private void changeMenuStatus() {

        //点击、删除状态的设置
        if (getCheckedCount() == 0) {
            mBtnAddBook.setText(getString(R.string.select));
            //设置某些按钮的是否可点击
            setMenuClickable(false);

            if (mCbSelectAll.isChecked()) {
                setChecked(false);
                mCbSelectAll.setChecked(isCheckedAll());
            }

        } else {
            mBtnAddBook.setText(getString(R.string.select)+getCheckedCount());
            setMenuClickable(true);

            //全选状态的设置

            //如果选中的全部的数据，则判断为全选
            if (getCheckedCount() == getCheckableCount()) {
                //设置为全选
                setChecked(true);
                mCbSelectAll.setChecked(isCheckedAll());
            }
            //如果曾今是全选则替换
            else if (isCheckedAll()) {
                setChecked(false);
                mCbSelectAll.setChecked(isCheckedAll());
            }
        }

        //重置全选的文字
        if (isCheckedAll()) {
            mCbSelectAll.setText("取消");
        } else {
            mCbSelectAll.setText("全选");
        }

    }

    /**
     * 搜索文件
     */
    private void scanFiles(TextView textView) {
        LoadingHelper.getInstance().showLoading(mContext,false);
        addDisposable(FileUtils.getSDTxtFile(textView)
                .compose(RxUtils::toSimpleSingle)
                .subscribe(
                        files -> {
                            LoadingHelper.getInstance().hideLoading();
                            tv_searchpath.setVisibility(View.GONE);
                            mBtnScan.setEnabled(true);
                            mFileBeans.clear();
                            if (files.size() == 0) {
                                loadlayout.setStatus(LoadingLayout.Empty);
                            } else {
                                loadlayout.setStatus(LoadingLayout.Success);
                                for (File file : files) {
                                    LocalFileBean localFileBean = new LocalFileBean();
                                    localFileBean.setSelect(false);
                                    localFileBean.setFile(file);
                                    mFileBeans.add(localFileBean);
                                }
                                mAdapter.notifyDataSetChanged();

                                //反馈
                                if (mListener != null) {
                                    mListener.onCategoryChanged();
                                }
                            }
                        }));
    }

    @OnClick({R.id.file_system_cb_selected_all, R.id.file_system_btn_add_book, R.id.file_system_btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.file_system_cb_selected_all:
                //设置全选状态
                setCheckedAll(mCbSelectAll.isChecked());

                //改变菜单状态
                changeMenuStatus();
                break;
            case R.id.file_system_btn_add_book:
                //获取选中的文件
                List<File> files = getCheckedFiles();
                String s = "";
                for (File file : files) {
                    //判断文件是否存在
                    if (!file.exists()) continue;

                    s += "||||File:" + file.getAbsolutePath() + "--" + file.getName();
                    LogUtils.e("---选择文件--File:" + file.getAbsolutePath() + "--" + file.getName());
                }

                //这里是带回的信息，以后可以直接用了
                Intent intent = new Intent();
                intent.putExtra(Constant.TAG_FILERESULT, s);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();

                //转换成CollBook,并存储
//                List<CollBookBean> collBooks = convertCollBook(files);
//                CollBookHelper.getsInstance().saveBooks(collBooks);
                //设置数据为false
                setCheckedAll(false);
                //改变菜单状态
                changeMenuStatus();
                //改变是否可以全选
                changeCheckedAllStatus();
                //提示加入书架成功
//                ToastUtils.show(getResources().getString(R.string.wy_file_add_succeed, collBooks.size()));
                break;
            case R.id.file_system_btn_delete:
                //弹出，确定删除文件吗。
                new AlertDialog.Builder(mContext)
                        .setTitle("删除文件")
                        .setMessage("确定删除文件吗?")
                        .setPositiveButton(getResources().getString(R.string.wy_common_sure), (dialog, which) -> {
                            //删除选中的文件
                            deleteCheckedFiles();
                            //提示删除文件成功
                            ToastUtils.show("删除文件成功");
                        })
                        .setNegativeButton(getResources().getString(R.string.wy_common_cancel), null)
                        .show();
                break;
        }
    }

}
