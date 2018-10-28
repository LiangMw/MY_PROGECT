package com.example.javatest.main;


import com.example.javatest.sort.NewQuickSort;

import java.util.Random;

/**
 * 排序的主函数
 */
public class MyClass {


    public static void main(String[] args) {

        int[] array;
        int[] array2;
        long begin = System.currentTimeMillis();
//        array = generateArray(6);
        array2 = generateArray(10000000);
//        printArray(array2);

        array = new int[array2.length];
        for (int i =0;i<array2.length;i++){
            array[i] = array2[i];
        }

        long end = System.currentTimeMillis();
        CountTime("GenerateArray:useTime:", begin, end);


        NewQuickSort newQuickSort = new NewQuickSort();

        System.out.println("quickSort_W排序后：");
        begin = System.currentTimeMillis();
//        new MergeSort().mergeSort(array);
        newQuickSort.quickSort_W(array);
        end = System.currentTimeMillis();
        CountTime("quickSort_W:useTime:", begin, end);
//        printArray(array)

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("quickSort_A排序后：");
        begin = System.currentTimeMillis();
//        new MergeSort().mergeSort(array);
        newQuickSort.quickSort_A(array2);
        end = System.currentTimeMillis();
        CountTime("quickSort_A:useTime:", begin, end);
//        printArray(array2);


//        System.out.println("排序后：");
        begin = System.currentTimeMillis();
//        new MergeSort().mergeSort(array);
//        new QuickSort().Quicksort(array);
//        new InsertSort().insertSort(array);
        end = System.currentTimeMillis();
//        CountTime("InsertSort:useTime:", begin, end);

    }

    /**
     * 打印int数组
     *
     * @param a
     */
    public static void printArray(int[] a) {
        if (a == null || a.length < 1) {
            return;
        }
        int c = a.length;
        System.out.println("数组长:" + c);
        for (int i = 0; i < c; i++) {
            System.out.print(a[i] + "->");
            if ((i + 1) % 45 == 0) {
                System.out.println();
            }
        }
    }

    /**
     * 手写一个生成没有重复数字的随机数组
     */

    public static int[] generateArray(int count) {
        if (count == 0) {
            count = 10;
        }
        int[] a = new int[count];
        for (int i = 0; i < count; i++) {
            a[i] = i + 1;
        }
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
        return a;
    }

    public static void CountTime(String msg, long b, long e) {
        System.out.println(msg == null ? "" : msg + (e - b));
    }



    /**
     * 从小到大冒泡排序
     *
     * @param array
     */
    public static int[] bubbleSore(int[] array) {
        if (array == null || array.length < 1) return null;
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static void insertSort(int[] a) {
        if (a == null || a.length < 1) return;
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;

            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

}