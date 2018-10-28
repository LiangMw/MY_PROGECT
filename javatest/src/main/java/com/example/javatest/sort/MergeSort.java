package com.example.javatest.sort;

/**
 * Created by 梁明伟 on 2018/10/18.
 * Copyright © 2018年 CETC. All rights reserved.
 * 归并排序实现
 */
public class MergeSort {
    private static int num = 1;

    public  void mergeSort(int[] a) {
        int n = a.length;
        mergersort_c(a,0,n-1);
    }

    private void mergersort_c(int[] a, int l, int r) {
        if (l >= r) {//终止条件
            return;
        }
        int q = (l + r) / 2;//中间值
        mergersort_c(a, l, q);
        mergersort_c(a, (q + 1), r);
        merge(a, l, q, r);
    }

    /**
     * 合并的函数（important）
     *
     * @param a
     * @param left
     * @param m
     * @param right
     */
    private void merge(int[] a, int left, int m, int right) {

        //定义个临时数组用来存储临时的排序
        int[] temp = new int[right - left+1];//这里这样处理节约临时空间，也可以申请与a[]一样大小的数组空间。下边的逻辑要做相应的修改
        int i = left;//用来取前一部分数据
        int j = (m + 1);//用来区后一部分数据
        int k = 0;//临时数组的角标
        while (i <= m && j <= right) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= m) {
            temp[k++] = a[i++];
        }

        while ((j <= right)) {
            temp[k++] = a[j++];
        }

        //把临时数组中的数据拷贝的原来的数组中
//        System.out.println("第" + (num++) + "趟排序");
        int l = 0;
        while (left <= right) {
            a[left++] = temp[l];
//            System.out.print(temp[l] + "  ");
            l++;
        }
//        System.out.println()
    }
}
