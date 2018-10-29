package com.example.javatest.util;

import java.util.Random;

/**
 * Created by 梁明伟 on 2018/10/29.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class Method {

    /**
     * 生成有序数组
     * @param count
     * @return
     */
    public static int[] generateSortArray(int count){
        return generateArray(count,true);
    }
    /**
     * 生成无序数组
     * @param count
     * @return
     */
    public static int[] generateUnSortArray(int count){
        return generateArray(count,false);
    }
    /**
     * 手写一个生成没有重复数字的随机数组
     */

    public static int[] generateArray(int count,boolean isSort) {
        if (count == 0) {
            count = 10;
        }
        int[] a = new int[count];
        for (int i = 0; i < count; i++) {
            a[i] = i + 1;
        }
        if(!isSort) {//要生成无序数组的代码，
            Random r = new Random();
            for (int i = 0; i < count; i++) {
                int t1 = Math.abs(r.nextInt()) % count;
                int t2 = Math.abs(r.nextInt()) % count;
                if (t1 != t2) {
                    int temp = a[t1];
                    a[t1] = a[t2];
                    a[t2] = temp;
                }
            }
        }
        return a;
    }
}
