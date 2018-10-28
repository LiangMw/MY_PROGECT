package com.example.javatest.sort;

/**
 * Created by 梁明伟 on 2018/10/21.
 * Copyright © 2018年 CETC. All rights reserved.
 * 以第一个数为基准  借助哨兵实现快速排序
 */
public class NewQuickSort {


    public void quickSort_W(int[] a) {
        if(a ==null || a.length<1) {
            return;
        }

        int n = a.length;
        quicksort_c(a, 0, n-1);
//        parttion(a,0,n-1);
    }
    public void quickSort_A(int[] a) {
        if(a ==null || a.length<1) {
            return;
        }

        int n = a.length;
        parttions(a,0,n-1);
    }
    private void quicksort_c(int[] a,int l,int r){
        if(l>=r) {
            return;

        }
        int m = parttion(a,l,r);
        quicksort_c(a, l, m-1);
        quicksort_c(a, m+1, r);
    }
    /**
     * 与上一个快排的区别就在这里
     * @param a
     * @return
     */
    private int parttion(int[] a,int l,int r){

        int point = a[l];
        int i = l;
        int j = r;
        while ( i != j){
            while (a[j]>=point && i<j){
                j--;
            }

            while (a[i]<=point && i<j){
                i++;
            }
            if(i<j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] =temp;

            }


        }

        a[l] = a[i];
        a[i] = point;
//        parttion(a,l,i-1);
//        parttion(a,i+1,r);
//        System.out.println("i:"+i+"---"+point);
        return i;
    }


    /**
     * 与上一个快排的区别就在这里
     * @param a
     * @return
     */
    private void parttions(int[] a,int l,int r){
        if(l>=r) {
            return ;
        }
        int point = a[l];
        int i = l;
        int j = r;
        while ( i != j){
            while (a[j]>=point && i<j){
                j--;
            }

            while (a[i]<=point && i<j){
                i++;
            }
            if(i<j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] =temp;

            }


        }

        a[l] = a[i];
        a[i] = point;
        parttions(a,l,i-1);
        parttions(a,i+1,r);
//        System.out.println("i:"+i+"---"+point);
    }
}
