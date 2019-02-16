package com.example.javatest.java;

/**
 * Created by 梁明伟 on 2018/12/3.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class ATest<T extends String> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public <E> void getUsername(E e){


    }
}
