package com.integrate.mingweidev.mvp.view.fragment.music;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.integrate.mingweidev.R;
import com.integrate.mingweidev.mvp.base.BaseFragment;
import com.integrate.mingweidev.mvp.contract.CMusic;
import com.integrate.mingweidev.mvp.presenter.PMusicImpl;
import com.integrate.mingweidev.mvp.view.MainActivity;
import com.integrate.mingweidev.mvp.view.adapter.BaseViewPageAdapter;
import com.integrate.mingweidev.mvp.view.fragment.FunctionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author LuLiang
 * @github https://github.com/LiangLuDev
 */

public class MusicFragment extends BaseFragment<PMusicImpl> implements CMusic.IVMusic {

    @BindView(R.id.nts_classify)
    NavigationTabStrip ntsClassify;
    @BindView(R.id.vp_classify)
    ViewPager vpClassify;
    Unbinder unbinder;

    String[] titles = {"网络歌曲", "本地歌曲"};
    private List<Fragment> mFragments = new ArrayList<>();

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_music;
    }

    @Override
    public void createPresenter() {
        mPresenter = new PMusicImpl(mContext, this);
    }

    @Override
    protected void initView() {
        mFragments.add(new NetMusicFragment());
        mFragments.add(FunctionFragment.newInstance());
        vpClassify.setAdapter(new BaseViewPageAdapter(getActivity().getSupportFragmentManager(), titles, mFragments));
        vpClassify.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((MainActivity) getActivity()).setLeftSlide(position == 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpClassify.setOffscreenPageLimit(2);
        ntsClassify.setTitles(titles);
        ntsClassify.setViewPager(vpClassify);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
