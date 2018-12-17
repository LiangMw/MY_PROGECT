package com.example.javatest;

/**
 * Created by 梁明伟 on 2018/12/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class Animal<T> {

    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public <R> R getClass(Class<R> r) throws IllegalAccessException, InstantiationException {
        return r.newInstance();
    }
}
