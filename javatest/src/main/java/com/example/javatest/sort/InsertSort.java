package com.example.javatest.sort;

/**
 * Created by 梁明伟 on 2018/10/20.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class InsertSort {
    public void insertSort(int[] a){
        if(a == null || a.length<1) return;
        int n = a.length;
        for (int i =1;i<n;i++){//从第二个元素开始来进行比较
            int val = a[i];//先把这个要比较的元素保存起来
            int j = i-1;//要比较的元素前一个元素的角标 用来倒着比较

            for (;j>=0;j--){//要比较0时候的元素，是因为要比较数组中的第一个啊
                if(a[j]>val) {//前一个元素小于要比较的目标元素的时候 需要做数据交换
                    a[j+1] = a[j];//数据交换，只需要把元素向后移动一个位置就可以，因为后边的元素是已经保存来下的
                }else {
                    break;//进入这里 说明已经找到了，插入位置，跳出本层循环了，j-- 不再执行
                }
            }
            //内层循环执行完毕，此时j+1的位置为要插入的位置（不知道为啥？ 去画图吧）
            a[j+1] = val;

        }
    }
}
