package com.integrate.mingweidev.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.integrate.mingweidev.mvp.base.BaseFragmentActivity;
import com.integrate.mingweidev.mvp.view.LoginActivity;
import com.integrate.mingweidev.mvp.view.fragment.FragmentPages;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by 梁明伟 on 2018/10/23.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class AppMethod {

    private static String guid;
    private static String token;

    /**
     * 跳转到simplebackactivity(不带回调的启动)
     *
     * @param cxt
     * @param page
     */
    public static void postShow(Activity cxt, FragmentPages page) {
        postShow(cxt, page, new Bundle());
    }

    /**
     * 跳转到simplebackactivity(不带回调的启动)
     *
     * @param cxt
     * @param page
     * @param data
     */
    public static void postShow(Activity cxt, FragmentPages page,
                                Bundle data) {
        Intent intent = new Intent(cxt, BaseFragmentActivity.class);
        intent.putExtra(Constant.CONTENT_KEY, page.getValue());
        intent.putExtra(Constant.DATA_KEY, data);
        cxt.startActivity(intent);
    }

    /**
     * 从推送跳转到simplebackactivity(不带回调的启动)
     *
     * @param cxt
     * @param page
     * @param data
     */
    public static void postShowFromReceiver(Context cxt, FragmentPages page,
                                            Bundle data) {
        Intent intent = new Intent(cxt, BaseFragmentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constant.CONTENT_KEY, page.getValue());
        intent.putExtra(Constant.DATA_KEY, data);
        cxt.startActivity(intent);
    }

    /**
     * activity跳转到SimpleBackActivity时，只能使用该方法跳转(带回调的启动)
     *
     * @param code 启动码
     * @param page 要显示的Fragment
     */
    public static void postShowForResult(Activity activity, int code,
                                         FragmentPages page) {
        postShowForResult(activity, code, page, new Bundle());
    }

    /**
     * activity跳转到SimpleBackActivity时，只能使用该方法跳转(带回调的启动)
     *
     * @param code 启动码
     * @param page 要显示的Fragment
     * @param data 传递的Bundle数据
     */
    public static void postShowForResult(Activity activity, int code,
                                         FragmentPages page, Bundle data) {
        Intent intent = new Intent(activity,
                BaseFragmentActivity.class);
        intent.putExtra(Constant.CONTENT_KEY, page.getValue());
        intent.putExtra(Constant.DATA_KEY, data);
        activity.startActivityForResult(intent, code);
    }

    public static void postShowForResult(Fragment fragment, int code,
                                         FragmentPages page) {
        postShowForResult(fragment, code, page, new Bundle());
    }

    /**
     * 跳转到SimpleBackActivity时，只能使用该方法跳转(带回调的启动)
     *
     * @param code 启动码
     * @param page 要显示的Fragment
     * @param data 传递的Bundle数据
     */
    public static void postShowForResult(Fragment fragment, int code,
                                         FragmentPages page, Bundle data) {
        Intent intent = new Intent(fragment.getActivity(),
                BaseFragmentActivity.class);
        intent.putExtra(Constant.CONTENT_KEY, page.getValue());
        intent.putExtra(Constant.DATA_KEY, data);
        fragment.startActivityForResult(intent, code);
    }

    public static void getPermission(Activity activity) {

//        List<PermissonItem> permissonItems = new ArrayList<PermissonItem>();
//        permissonItems.add(new PermissonItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_memory));
//        permissonItems.add(new PermissonItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
//        HiPermission.create(activity)
//                .permissions(permissonItems)
//                .checkMutiPermission(...);

    }

    /**
     * 获取guid
     *
     * @return
     */
    public static String getGuid() {
        if (TextUtils.isEmpty(Constant.GUID))
            Constant.GUID = SharedPreUtils.getInstance().getString(Constant.TAG_GUID, "");
        return guid;
    }

    /**
     * 获取guid
     *
     * @param url
     * @return
     */
    public static String getSign(String url,String token) {
        String sign = "";
        String[] arr_urls = url.split("/");
        if (arr_urls.length <= 0) {
            return sign;
        }
        int len = arr_urls.length;
        if (len < 2) {
            return sign;
        }
        String str = "/" + arr_urls[len - 2] + "/" + arr_urls[len - 1] + "?" + "token=" + token;
        try {
            sign = MD5Utils.encrypt(str);
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void goLogin(Activity mActivity){
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
        SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_TOKEN);
        SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_GUID);
        mActivity.finish();
    }

    /**
     * 获取guid
     *
     * @return
     */
    public static String getToken() {
        if (TextUtils.isEmpty(Constant.TOKEN))
            Constant.TOKEN = SharedPreUtils.getInstance().getString(Constant.TAG_TOKEN, "");
        return token;
    }

    public void getPermissions(Activity activity) {

        HiPermission.create(activity)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
//                        showToast("用户关闭权限申请");
                        SnackBarUtils.makeShort(activity.getWindow().getDecorView(), "读写权限被禁止,移步到应用管理允许权限");
                    }

                    @Override
                    public void onFinish() {
//                        showToast("所有权限申请完成");
                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                    }
                });

    }


}
