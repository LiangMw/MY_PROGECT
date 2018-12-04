package com.example.javatest.interfaces;

/**
 * Created by 梁明伟 on 2018/12/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface Generator<T,E> {
    T next();
    E prev();
}
