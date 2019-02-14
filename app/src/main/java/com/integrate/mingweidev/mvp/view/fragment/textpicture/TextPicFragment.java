package com.integrate.mingweidev.mvp.view.fragment.textpicture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.bean.PicBookBean;
import com.integrate.mingweidev.mvp.contract.CResult;
import com.integrate.mingweidev.mvp.presenter.PTextPic;
import com.integrate.mingweidev.utils.ImageUtils;
import com.integrate.mingweidev.utils.LogUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;
import com.integrate.mingweidev.widget.theme.ColorTextView;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 梁明伟 on 2019/1/30.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class TextPicFragment extends BaseFragment<PTextPic> implements InvokeListener, TakePhoto.TakeResultListener,CResult<PicBookBean> {

    @BindView(R.id.tv_select)
    ColorTextView tvSelect;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    @BindView(R.id.tv_result)
    EditText tv_result;

    Unbinder unbinder;
    //    TakePhoto takePhoto=new TakePhotoImpl(getActivity(),this);
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 传入布局文件
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_textpic;
    }

    /**
     * 创建presenter实例
     */
    @Override
    public void createPresenter() {
        mPresenter = new PTextPic(getActivity(),this);
    }

    /**
     * 初始化
     */
    @Override
    protected void initView() {

        //获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
        //设置压缩参数
        compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, false);//设置为需要压缩

    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void setTitleBar(ActionBarRes mActionBarRes) {
        super.setTitleBar(mActionBarRes);
        mActionBarRes.mTitle = getString(R.string.fc_textpic);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    @Override
    public void takeSuccess(TResult result) {
        String mIconPath = result.getImage().getOriginalPath();
        ToastUtils.show(mIconPath);
        ImageLoadManage.getInstance().display(getActivity(),iv_pic, mIconPath);
        Map<String,String> map = new HashMap<>();
        map.put("image", ImageUtils.imageToBase64Str(mIconPath));
        mPresenter.getpictext(map);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void takeCancel() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_select)
    public void onViewClicked() {

        /**
         * 设置内容区域为简单列表项
         */
        final String[] items = {"相册", "拍摄"};
        new MaterialDialog.Builder(getActivity())
                .title("选择照片方式")
                .items(items)
                .itemsCallback((dialog, itemView, position, text) -> {
                    switch (position) {
                        case 0:
                            dialog.dismiss();
                            imageUri = getImageCropUri();
                            //从相册中选取图片并裁剪
//                                    takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                            //从相册中选取不裁剪
                            //takePhoto.onPickFromGallery();
                            takePhoto.onPickMultiple(1);
                            break;
                        case 1:
                            dialog.dismiss();
                            imageUri = getImageCropUri();
                            //拍照并裁剪
                            takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                            //仅仅拍照不裁剪
                            //takePhoto.onPickFromCapture(imageUri);
                            break;
                    }
                })
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void success(PicBookBean e) {
        LogUtils.e("------------:"+e.getCode()+"---"+e.getMsg()+"----"+e.getResult().getText()+"");
        if(e.getResult()!=null && !TextUtils.isEmpty(e.getResult().getText())) {
            tv_result.setText(e.getResult().getText());
        }else{
            tv_result.setText("未转换成功");
        }
    }

    @Override
    public void error(String msg) {
        ToastUtils.show(msg);
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
}
