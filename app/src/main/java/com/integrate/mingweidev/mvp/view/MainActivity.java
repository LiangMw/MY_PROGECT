package com.integrate.mingweidev.mvp.view;

import android.animation.Animator;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.MainBaseActivity;
import com.integrate.mingweidev.mvp.model.MainMenuBean;
import com.integrate.mingweidev.mvp.view.adapter.MainMenuAdapter;
import com.integrate.mingweidev.mvp.view.fragment.FunctionFragment;
import com.integrate.mingweidev.utils.BaseUtils;
import com.integrate.mingweidev.utils.SharedPreUtils;
import com.integrate.mingweidev.utils.SnackBarUtils;
import com.integrate.mingweidev.utils.ThemeUtils;
import com.integrate.mingweidev.utils.ToastUtils;
import com.integrate.mingweidev.widget.ResideLayout;
import com.integrate.mingweidev.widget.theme.ColorRelativeLayout;
import com.integrate.mingweidev.widget.theme.ColorUiUtil;
import com.integrate.mingweidev.widget.theme.Theme;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MainBaseActivity implements ColorChooserDialog.ColorCallback {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMenu();
        fragmentManager = getSupportFragmentManager();
        switchFragment("功能");
    }

    private void initMenu() {
        mTvDesc.setText("jsjflasjk士大夫撒分散式撒的发顺丰撒的发生的fdsj");
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
                case "扫描书籍":
                    /*RxPermissions rxPermissions = new RxPermissions(this);
                    rxPermissions
                            .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(permission -> {
                                if (permission.granted) {
                                    // 用户已经同意该权限
                                    this.switchFragment(name);
                                    mTvToolBarTitle.setText(name);
                                    mIvToolBarBack.setImageResource(menuBeans.get(position).getIcon());
                                    mResideLayout.closePane();
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                    ToastUtils.show("用户拒绝开启读写权限");
                                    mResideLayout.closePane();
                                } else {
                                    // 用户拒绝了该权限，并且选中『不再询问』
                                    mResideLayout.closePane();
                                    SnackBarUtils.makeShort(MainActivity.this.getWindow().getDecorView(), "读写权限被禁止,移步到应用管理允许权限").show("去设置", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            BaseUtils.getAppDetailSettingIntent(mContext, getPackageName());
                                        }
                                    });

                                }
                            });*/
                    break;
                case "书架":
                   /* switchFragment(name);
                    mTvToolBarTitle.setText(name);
                    mIvToolBarBack.setImageResource(menuBeans.get(position).getIcon());
                    mResideLayout.closePane();*/
                    break;
                case "功能":
                   /* switchFragment(name);
                    mTvToolBarTitle.setText(name);
                    mIvToolBarBack.setImageResource(menuBeans.get(position).getIcon());
                    mResideLayout.closePane();*/
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
                    foundFragment = FunctionFragment.newInstance();
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

    @OnClick({R.id.iv_avatar, R.id.tv_theme, R.id.tv_setting})
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
    public void onBackPressed() {
        if (mResideLayout.isOpen()) {
            mResideLayout.closePane();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - fristTime < 2000) {
                finish();
            } else {
                SnackBarUtils.makeShort(getWindow().getDecorView(), "再点击一次退出应用").show();
                fristTime = System.currentTimeMillis();
            }
        }
    }

}
