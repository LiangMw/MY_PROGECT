package com.example.javatest.bsearch;

/**
 * Created by 梁明伟 on 2018/10/29.
 * Copyright © 2018年 CETC. All rights reserved.
 * 总结：
 * 1.需要向右找的时候就 low = mid +1;
 * 2.需要向左找的时候就 hihg = mid -1；
 * 3.把题目中的条件作为判断的依据就可以，比如 大于等于、小于等于
 *
 */
public class BSearch {

    /**
     * 普通的最简单的二分查找
     * 从一个无重复数据 且有序的数组中查找给定值
     *
     * @param a
     * @param value
     * @return
     */
    public int bSearch(int[] a, int value) {
        if (a == null || a.length < 1) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;

            } else if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            }

        }
        return -1;
    }

    /**
     * 二分查找 第一个给定值
     * 从一个有重复数据 且有序的数组中查找给定值
     *
     * @param a
     * @param value
     * @return
     */
    public int bSearchFirst(int[] a, int value) {
        if (a == null || a.length < 1) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                if (mid - 1 == 0 || a[mid - 1] != value) {//这里与查找最后一个不同
                    return mid;
                } else {
                    high = mid - 1;//这里与查找最后一个不同
                }
            } else if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            }

        }
        return -1;
    }


    /**
     * 二分查找 最后一个给定值
     * 从一个有重复数据 且有序的数组中查找给定值
     *
     * @param a
     * @param value
     * @return
     */
    public int bSearchLast(int[] a, int value) {
        if (a == null || a.length < 1) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                if (mid + 1 >a.length-1 || a[mid + 1] != value) {//这里与查找第一个不同
                    return mid;
                } else {
                    low = mid + 1;//这里与查找第一个不同
                }
            } else if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            }

        }
        return -1;
    }
    /**
     * 二分查找 查找第一个大于等于给定值的元素
     * 从一个有重复数据 且有序的数组中查找给定值
     *
     * @param a
     * @param value
     * @return
     */
    public int bSearchFirstBiger(int[] a, int value) {
        if (a == null || a.length < 1) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid+1;
            } else if (a[mid]>= value){
                if(mid - 1<0||a[mid-1]<value) {
                   return mid;

                }else {
                    high = mid -1;
                }
            }
        }
        return -1;
    }
    /**
     * 二分查找 查找最后一个小于等于给定值的元素
     * 从一个有重复数据 且有序的数组中查找给定值
     *
     * @param a
     * @param value
     * @return
     */
    public int bSearchLastSmaler(int[] a, int value) {
        if (a == null || a.length < 1) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid-1;
            } else if (a[mid]<=value){
                if(mid >= a.length-1||a[mid+1]>value) {//这里比较关键
                   return mid;
                }else {
                    low = mid +1;
                }
            }
        }
        return -1;
    }
}
