package com.integrate.mingweidev.mvp.view.fragment;


import com.integrate.mingweidev.mvp.view.fragment.files.FilesCategoryFragment;
import com.integrate.mingweidev.mvp.view.fragment.test.TestFragment;

/**
 * fragment管理枚举类
 */
public enum FragmentPages {

    //顺风车身份选择界面
    WINDCAR_IDENTITYCHOICE(1001, FilesCategoryFragment.class),
    WINDCAR_TestFragment(1002,TestFragment.class);

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
