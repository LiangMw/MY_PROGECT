package com.example.javatest.main;

import com.example.javatest.bsearch.BSearch;
import com.example.javatest.util.Method;

/**
 * 二分查找的主函数
 */

public class BSearchMain {

    public static void main(String[] arr) {
        int[] a = Method.generateSortArray(10);
        a[5] = 5;
        a[6] = 6;
        a[7] = 6;
        a[8] = 6;
        System.out.print("-------------------:" + new BSearch().bSearchLastSmaler(a, 10));
    }
}
