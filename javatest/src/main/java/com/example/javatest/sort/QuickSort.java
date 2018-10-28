package com.example.javatest.sort;

/**
 * Created by 梁明伟 on 2018/10/19.
 * Copyright © 2018年 CETC. All rights reserved.
 * 以最后一个数为基准数 实现快速排序
 *
 */
public class QuickSort {
    public void Quicksort(int [] a){
        if(a ==null || a.length<1) {
             return;
        }
        int n = a.length;
        quicksort_c(a,0,n-1);
    }

    /**
     * 快排递归函数
     * @param a
     * @param l
     * @param r
     */
    private void quicksort_c(int[] a,int l,int r){

        //终止条件
        if(l>=r) {
            return;
        }
        int m = parttion(a,l,r);
        quicksort_c(a,l,m-1);
        quicksort_c(a,m+1,r);
    }

    /**
     * 最重要的分区函数，求得中间节点的position
     * @param a
     * @param l
     * @param r
     * @return
     */
    private int parttion(int[]a,int l,int r){

        int point = a[r];//取最后一个元素为分界点
        int i = l;//记录要交换的位置
        int j = l;//记录比较的位置
        while (j<=r){//这里j<=r  或者 j<r 都行，后者性能更好一些，少了一次比较
            if(a[j]<point) {
                int tem = a[j];
                a[j] = a[i];
                a[i] = tem;
                i++;
            }
            j++;
        }
        int t = a[i];
        a[i] = a[r];
        a[r] = t;
//        System.out.println("i=" + i+"----"+a[i]);
        return i;
    }
}
