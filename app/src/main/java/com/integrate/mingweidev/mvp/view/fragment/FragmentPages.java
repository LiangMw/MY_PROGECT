package com.integrate.mingweidev.mvp.view.fragment;


import com.integrate.mingweidev.mvp.view.fragment.files.FilesCategoryFragment;
import com.integrate.mingweidev.mvp.view.fragment.files.LocalFilesFragment;
import com.integrate.mingweidev.mvp.view.fragment.music.NetMusicListFragment;
import com.integrate.mingweidev.mvp.view.fragment.news.NewsMainFragment;
import com.integrate.mingweidev.mvp.view.fragment.test.TestFragment;

/**
 * fragment管理枚举类
 */
public enum FragmentPages {

    FILE_MANAGE(1002, FilesCategoryFragment.class),
    FILE_SEARCH(1003, LocalFilesFragment.class),
    NEWS_LIST(1004, NewsMainFragment.class),
    SONGS_LIST(1005, NetMusicListFragment.class),
    WINDCAR_TestFragment(1001,TestFragment.class);

    private Class<?> clazz;
    private int value;

    FragmentPages(int value, Class<?> cls) {
        this.clazz = cls;
        this.value = value;
    }

    public static Class<?> getPageByValue(int value) {
        for (FragmentPages p : values()) {
            if (p.getValue() == value)
                return p.getClazz();
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
