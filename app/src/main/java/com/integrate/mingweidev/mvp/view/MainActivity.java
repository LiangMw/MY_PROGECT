package com.integrate.mingweidev.mvp.view;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.integrate.mingweidev.MYApplication;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.MainBaseActivity;
import com.integrate.mingweidev.mvp.bean.MainMenuBean;
import com.integrate.mingweidev.mvp.bean.UserInfoBean;
import com.integrate.mingweidev.mvp.contract.CLogin;
import com.integrate.mingweidev.mvp.model.login.MLogin;
import com.integrate.mingweidev.mvp.presenter.login.PLogin;
import com.integrate.mingweidev.mvp.view.adapter.MainMenuAdapter;
import com.integrate.mingweidev.mvp.view.fragment.FragmentPages;
import com.integrate.mingweidev.mvp.view.fragment.music.MusicFragment;
import com.integrate.mingweidev.utils.AppMethod;
import com.integrate.mingweidev.utils.BaseUtils;
import com.integrate.mingweidev.utils.Constant;
import com.integrate.mingweidev.utils.LogUtils;
import com.integrate.mingweidev.utils.SharedPreUtils;
import com.integrate.mingweidev.utils.SnackBarUtils;
import com.integrate.mingweidev.utils.ThemeUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.utils.imageload.ImageLoadManage;
import com.integrate.mingweidev.widget.ResideLayout;
import com.integrate.mingweidev.widget.theme.ColorRelativeLayout;
import com.integrate.mingweidev.widget.theme.ColorUiUtil;
import com.integrate.mingweidev.widget.theme.Theme;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

public class MainActivity extends MainBaseActivity implements ColorChooserDialog.ColorCallback, CLogin.IVLogin<UserInfoBean> {

    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.top_menu)
    LinearLayout mTopMenu;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.tv_theme)
    TextView mTvTheme;
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.bottom_menu)
    LinearLayout mBottomMenu;
    @BindView(R.id.menu)
    ColorRelativeLayout mMenu;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.resideLayout)
    ResideLayout mResideLayout;
    @BindView(R.id.iv_toolbar_more)
    AppCompatImageView mIvToolBarMore;
    @BindView(R.id.iv_toolbar_back)
    AppCompatImageView mIvToolBarBack;
    @BindView(R.id.tv_toolbar_title)
    TextView mTvToolBarTitle;

    private MainMenuAdapter mainMenuAdapter;
    private List<MainMenuBean> menuBeans = new ArrayList<>();
    private long fristTime = 0;
    private FragmentManager fragmentManager;
    private String currentFragmentTag;
    private int CHOOSEFILE_CODE = 10001;
    private PLogin pLogin;
    private boolean islogin = false;
    private View.OnClickListener leftlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mResideLayout.openPane();
        }
    };
    private View.OnClickListener rightlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMenu();
        fragmentManager = getSupportFragmentManager();
        switchFragment("功能");
        initThemeToolBar("音乐盒子", false, R.drawable.ic_classify, leftlistener);
    }

    private void initMenu() {
        mTvDesc.setText("愿得一人心 免得再相亲。................");
        mTvDesc.setSelected(true);
        BaseUtils.setIconDrawable(mTvSetting, R.drawable.ic_setting);
        BaseUtils.setIconDrawable(mTvTheme, R.drawable.ic_theme);
        getMenuData();
        mRvMenu.setLayoutManager(new LinearLayoutManager(this));
        mainMenuAdapter = new MainMenuAdapter(menuBeans);
        mRvMenu.setAdapter(mainMenuAdapter);
        mainMenuAdapter.setOnItemClickListener((adapter, view, position) -> {
            String name = menuBeans.get(position).getName();
            switch (name) {
                case "文件管理":
                    if (!HiPermission.checkPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        HiPermission.create(MainActivity.this)
                                .title("请允许以下权限")
                                .msg("为了收复钓鱼岛")
                                .style(R.style.AppTheme)
                                .filterColor(ThemeUtils.getThemeColor())
                                .animStyle(R.anim.popup_enter)
                                .checkSinglePermission(Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionCallback() {
                                    @Override
                                    public void onClose() {
                                        SnackBarUtils.makeShort(MainActivity.this.getWindow().getDecorView(), "读写权限被禁止");
                                    }

                                    @Override
                                    public void onFinish() {

                                    }

                                    @Override
                                    public void onDeny(String permission, int position) {

                                    }

                                    @Override
                                    public void onGuarantee(String permission, int position) {
                                        AppMethod.postShowForResult(MainActivity.this, CHOOSEFILE_CODE, FragmentPages.FILE_MANAGE);
                                    }
                                });
                    } else {
                        AppMethod.postShowForResult(MainActivity.this, CHOOSEFILE_CODE, FragmentPages.FILE_MANAGE);
                    }
                    break;
                case "扫描文件":
                    if (!HiPermission.checkPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        HiPermission.create(MainActivity.this)
                                .title("请允许以下权限")
                                .msg("为了收复钓鱼岛")
                                .style(R.style.AppTheme)
                                .filterColor(ThemeUtils.getThemeColor())
                                .animStyle(R.anim.popup_enter)
                                .checkSinglePermission(Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionCallback() {
                                    @Override
                                    public void onClose() {
                                        SnackBarUtils.makeShort(MainActivity.this.getWindow().getDecorView(), "读写权限被禁止");
                                    }

                                    @Override
                                    public void onFinish() {

                                    }

                                    @Override
                                    public void onDeny(String permission, int position) {

                                    }

                                    @Override
                                    public void onGuarantee(String permission, int position) {
                                        AppMethod.postShowForResult(MainActivity.this, CHOOSEFILE_CODE, FragmentPages.FILE_SEARCH);
                                    }
                                });
                    } else {
                        AppMethod.postShowForResult(MainActivity.this, CHOOSEFILE_CODE, FragmentPages.FILE_SEARCH);
                    }
                    break;
                case "功能":
                    mResideLayout.closePane();
                    break;
                case "缓存列表":
                   /* startActivity(BookDownloadActivity.class);
                    mResideLayout.closePane();*/
                    break;
                case "意见反馈":
                    /*startActivity(FeedBackActivity.class);
                    mResideLayout.closePane();*/
                    break;
                case "关于作者":
//                    startActivity(AboutMineActivity.class);
//                    mResideLayout.closePane();
                    /*mTvToolBarTitle.setText("看看");
                    switchFragment("发生大放送");*/
                    break;
                default:
                    ToastUtils.show("功能紧急开发中！！！");
                    break;
            }
        });
    }

    public void switchFragment(String name) {
        if (currentFragmentTag != null && currentFragmentTag.equals(name))
            return;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        Fragment foundFragment = fragmentManager.findFragmentByTag(name);

        if (foundFragment == null) {
            switch (name) {
                case "功能":
//                    foundFragment = BookClassifyFragment.newInstance();
                    foundFragment = MusicFragment.newInstance();
                    break;
                case "书架":
//                    foundFragment = BookShelfFragment.newInstance();
                    break;
                case "扫描书籍":
//                    foundFragment = ScanBookFragment.newInstance();
                    break;
                default:
//                    foundFragment = BookShelfFragment.newInstance();
//                    foundFragment = FilesCategoryFragment.newInstance();
                    break;
            }
        }

        if (foundFragment == null) {

        } else if (foundFragment.isAdded()) {
            ft.show(foundFragment);
        } else {
            ft.add(R.id.container, foundFragment, name);
        }
        ft.commit();
        currentFragmentTag = name;
    }

    private List<MainMenuBean> getMenuData() {
        menuBeans.clear();
        String[] menuName = getResources().getStringArray(R.array.main_menu_name);
        TypedArray menuIcon = getResources().obtainTypedArray(R.array.main_menu_icon);

        for (int i = 0; i < menuName.length; i++) {
            MainMenuBean menuBean = new MainMenuBean();
            menuBean.setName(menuName[i]);
            menuBean.setIcon(menuIcon.getResourceId(i, 0));
            menuBeans.add(menuBean);
        }
        return menuBeans;
    }

    @OnClick({R.id.iv_avatar, R.id.tv_theme, R.id.tv_setting, R.id.iv_toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
               /* String username = SharedPreUtils.getInstance()
                        .getString("username", "");
                if (username.equals("")) {
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(UserInfoActivity.class);
                }*/
                if (!islogin) {
                    AppMethod.goLogin(this);
                } else {
                    //进入个人信息
                }
                break;
            case R.id.tv_theme:
                new ColorChooserDialog.Builder(this, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;
            case R.id.tv_setting:
               /* if (mUsername.equals("")) {
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(SettingActivity.class);
                }*/
                islogin = false;
                AppMethod.goLogin(this);
                break;
            case R.id.iv_toolbar_back:
                mResideLayout.openPane();
                break;

        }
    }


    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor2Array(this, R.attr.colorPrimary))
            return;

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Blue);

        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            setTheme(R.style.RedTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Red);

        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Brown);

        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Green);

        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            setTheme(R.style.PurpleTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Purple);

        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            setTheme(R.style.TealTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Teal);

        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            setTheme(R.style.PinkTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Pink);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            setTheme(R.style.DeepPurpleTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.DeepPurple);

        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            setTheme(R.style.OrangeTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Orange);

        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            setTheme(R.style.IndigoTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Indigo);

        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            setTheme(R.style.LightGreenTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.LightGreen);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            setTheme(R.style.DeepOrangeTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.DeepOrange);

        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            setTheme(R.style.LimeTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Lime);

        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            setTheme(R.style.BlueGreyTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.BlueGrey);

        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            setTheme(R.style.CyanTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Cyan);

        }
        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }

    /**
     * 菜单是否可左滑
     *
     * @param isCanSlide
     */
    public void setLeftSlide(boolean isCanSlide) {
        mResideLayout.setCanLeftSlide(isCanSlide);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSEFILE_CODE) {
            if (data != null) {
                String dat = data.getStringExtra(Constant.TAG_FILERESULT);
                LogUtils.e("----------dat:" + dat);
            }

        }
    }

    @Override
    public void onBackPressed() {
        if (mResideLayout.isOpen()) {
            mResideLayout.closePane();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - fristTime < 2000) {
                MYApplication.removeAllActivity();
                finish();
            } else {
                SnackBarUtils.makeShort(getWindow().getDecorView(), "再点击一次退出应用").show();
                fristTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String guid = SharedPreUtils.getInstance().getString(Constant.TAG_GUID, "");
        String token = SharedPreUtils.getInstance().getString(Constant.TAG_TOKEN, "");
        if (TextUtils.isEmpty(token) || TextUtils.isEmpty(guid)) {
            //去登录的Activity
//            startActivity(LoginActivity.class);
            mTvDesc.setText("未登录");
            ImageLoadManage.getInstance().display(this, mIvAvatar, "http://mallcomment.holdsoft.cn/1510224399351", true);
        } else {
            if (pLogin == null) {
                pLogin = new PLogin(this, this, new MLogin());
                pLogin.getuserinfo(guid);
            }else{
                pLogin.getuserinfo(guid);
            }
        }
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

    @Override
    public void success(UserInfoBean bean) {
        if (Constant.SUCCESSCODE.equals(bean.getCode())) {
            mTvDesc.setText(bean.getResult().getUsername());
            ImageLoadManage.getInstance().display(this, mIvAvatar, bean.getResult().getFacepic(), true);
            islogin = true;
        }else {
            SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_TOKEN);
            SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_GUID);
        }
    }

    @Override
    public void error(String msg) {
        SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_TOKEN);
        SharedPreUtils.getInstance().sharedPreRemove(Constant.TAG_GUID);
    }
}
