package com.integrate.mingweidev.utils.imageload;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.utils.LogUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 梁明伟 on 2018/11/7.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class ImageSelectManage extends Activity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_select)
    TextView tvSelect;

    private InvokeParam invokeParam;
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectpicture);
        ButterKnife.bind(this);
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

//    @Override
//    public void takeSuccess(TResult result) {
//
////        String mIconPath = result.getImage().getOriginalPath();
//        String mIconPath = result.getImage().getCompressPath();
//        LogUtils.e("----------------mIconPath:"+mIconPath);
//        ImageLoadManage.getInstance().display(this,ivIcon, mIconPath);
//    }

    @Override
    public void takeSuccess(TResult result) {
        String mIconPath = result.getImage().getCompressPath();
        LogUtils.e("----------------mIconPath:"+mIconPath);
        ImageLoadManage.getInstance().display(this,ivIcon, mIconPath);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    @OnClick(R.id.tv_select)
    public void onViewClicked() {

        /**
         * 设置内容区域为简单列表项
         */
        final String[] items = {"相册", "拍摄"};
        new MaterialDialog.Builder(this)
                .title("选择照片方式")
                .items(items)
                .itemsCallback((dialog, itemView, position, text) -> {
                    switch (position) {
                        case 0:
                            dialog.dismiss();
                            imageUri = getImageCropUri();
                            //从相册中选取图片并裁剪
                            LogUtils.e("----0--imageUri:"+imageUri);
//                            takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                            //从相册中选取不裁剪
                            takePhoto.onPickFromGallery();
                            break;
                        case 1:
                            dialog.dismiss();
                            imageUri = getImageCropUri();
                            LogUtils.e("---1---imageUri:"+imageUri);
                            //拍照并裁剪
                            takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                            //仅仅拍照不裁剪
                            //takePhoto.onPickFromCapture(imageUri);
                            break;
                    }
                })
                .show();
//        imageUri = getImageCropUri();
        //拍照并裁剪
//        takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
        //仅仅拍照不裁剪
        //takePhoto.onPickFromCapture(imageUri);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
