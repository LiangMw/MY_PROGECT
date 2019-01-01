package com.example.javatest;

import com.example.javatest.interfaces.Generator;

/**
 * Created by 梁明伟 on 2018/12/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class FruitGenerator implements Generator<String,Integer>{

    @Override
    public String next() {
        return "next";
    }

    @Override
    public Integer prev() {
        return 0;
    }
}
