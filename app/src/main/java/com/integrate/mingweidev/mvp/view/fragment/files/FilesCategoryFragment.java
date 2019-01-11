package com.integrate.mingweidev.mvp.view.fragment.files;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.bean.LocalFileBean;
import com.integrate.mingweidev.mvp.view.adapter.ad_files.LocalFileAdapter;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.FileStack;
import com.integrate.mingweidev.utils.LogUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.widget.DividerItemDecoration;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Liang_Lu on 2017/12/1.
 */

public class FilesCategoryFragment extends BasesFileFragment {
    @BindView(R.id.file_category_tv_path)
    TextView mTvPath;
    @BindView(R.id.file_category_tv_back_last)
    TextView mTvBackLast;
    @BindView(R.id.rv_file_category)
    RecyclerView mRvFileCategory;
    @BindView(R.id.file_system_cb_selected_all)
    CheckBox mCbSelectAll;
    @BindView(R.id.file_system_btn_add_book)
    Button mBtnAddBook;
    @BindView(R.id.file_system_btn_delete)
    Button mBtnDelete;

    List<LocalFileBean> mFileBeans = new ArrayList<>();
    private FileStack mFileStack;

    public static FilesCategoryFragment newInstance() {
        FilesCategoryFragment fragment = new FilesCategoryFragment();
        return fragment;
    }

    @Override
    public void setTitleBar(ActionBarRes mActionBarRes) {
        super.setTitleBar(mActionBarRes);
        mActionBarRes.mTitle = "文件选择";
    }

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_files_category;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {

    }

    @Override
    public void initView() {
        mFileStack = new FileStack();
        mAdapter = new LocalFileAdapter(mFileBeans);
        mRvFileCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvFileCategory.addItemDecoration(new DividerItemDecoration(getContext()));
        mRvFileCategory.setAdapter(mAdapter);
        //设置某些按钮的不可点击
        setMenuClickable(false);
        File root = Environment.getExternalStorageDirectory();
        toggleFileTree(root);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            File file = mFileBeans.get(position).getFile();
            if (file.isDirectory()) {
                //保存当前信息。
                FileStack.FileSnapshot snapshot = new FileStack.FileSnapshot();
                snapshot.filePath = mTvPath.getText().toString();
                snapshot.files = new ArrayList<File>(mAdapter.getAllFiles());
                snapshot.scrollOffset = mRvFileCategory.computeVerticalScrollOffset();
                mFileStack.push(snapshot);
                //切换下一个文件
                toggleFileTree(file);
                setMenuClickable(false);
                mAdapter.resetCheckCount();
                changeMenuStatus();
            } else {
                //如果是已加载的文件，则点击事件无效。
                String id = file.getAbsolutePath();
//                if (CollBookHelper.getsInstance().findBookById(id) != null) {
//                    return;
//                }
                //点击选中
                mAdapter.setCheckedItem(position);
                changeMenuStatus();
                //反馈
                if (mListener != null) {
                    mListener.onItemCheckedChange(mAdapter.getItemIsChecked(position));
                }
            }

        });

        mTvBackLast.setOnClickListener(v -> {
            FileStack.FileSnapshot snapshot = mFileStack.pop();
            int oldScrollOffset = mRvFileCategory.computeHorizontalScrollOffset();
            if (snapshot == null) return;
            mTvPath.setText(snapshot.filePath);
            addFiles(snapshot.files);
            mRvFileCategory.scrollBy(0, snapshot.scrollOffset - oldScrollOffset);
            //反馈
            if (mListener != null) {
                mListener.onCategoryChanged();
            }
            setMenuClickable(false);
            if(mAdapter !=null) {
                mAdapter.resetCheckCount();
            }
            changeMenuStatus();
        });

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

    @Override
    public void onBackListener() {
        super.onBackListener();
        getActivity().finish();
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

    private void toggleFileTree(File file) {
        //路径名
        mTvPath.setText(getString(R.string.wy_file_path, file.getPath()));
        //获取数据
        File[] files = file.listFiles(new SimpleFileFilter());
        //转换成List
        List<File> rootFiles = Arrays.asList(files);
        //排序
        Collections.sort(rootFiles, new FileComparator());
        //加入
        addFiles(rootFiles);
        //反馈
        if (mListener != null) {
            mListener.onCategoryChanged();
        }
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
            mBtnAddBook.setText(getString(R.string.select)+ getCheckedCount());
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
     * 添加文件数据
     *
     * @param files
     */
    private void addFiles(List<File> files) {
        mFileBeans.clear();
        for (File file : files) {
            LocalFileBean localFileBean = new LocalFileBean();
            localFileBean.setSelect(false);
            localFileBean.setFile(file);
            mFileBeans.add(localFileBean);
        }
        mAdapter.notifyDataSetChanged();
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
                    LogUtils.e("---选择文件--File:" + file.getPath() + "--" + file.getName());
                    Constant.picpath = file.getPath();

                }

                //这里是带回的信息，以后可以直接用了
                Intent intent = new Intent();
                intent.putExtra(Constant.TAG_FILERESULT,s);
                getActivity().setResult(Activity.RESULT_OK,intent);
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

    public class FileComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            if (o1.isDirectory() && o2.isFile()) {
                return -1;
            }
            if (o2.isDirectory() && o1.isFile()) {
                return 1;
            }
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    public class SimpleFileFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            if (pathname.getName().startsWith(".")) {
                return false;
            }
            //文件夹内部数量为0
            if (pathname.isDirectory() && pathname.list().length == 0) {
                return false;
            }

            /**
             * 现在只支持TXT文件的显示
             */
            //文件内容为空,或者不以txt为开头
            if (!pathname.isDirectory() &&
//                    (pathname.length() == 0 || !pathname.getName().endsWith(FileUtils.SUFFIX_TXT))) {
                    (pathname.length() == 0)) {//|| !pathname.getName().endsWith(FileUtils.SUFFIX_TXT)//接收所有文件
                return false;
            }
            return true;
        }
    }

}
