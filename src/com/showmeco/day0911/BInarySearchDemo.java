package com.showmeco.day0911;

import java.io.CharArrayReader;

/**
 * PackageName: com.showmeco.day0911
 * ClassName: BInarySearchDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/11 20:56
 * @Version: 1.0
 */
public class BInarySearchDemo {
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        int i = binarySearch (array,  9);
        System.out.println(i);
    }


    public static int binarySearchRecursion(int[] array, int begin, int end, int num) {
        //二分查找递归写法

        if (begin > end) {
            return -1;//说明没有这个数
        }


        int mid = (begin + end) / 2;
        if (array[mid] < num) {
            return binarySearchRecursion(array, mid + 1, end, num);
        } else if (num < array[mid]) {
            return binarySearchRecursion(array, begin, mid - 1, num);
        } else {
            return mid;
        }
    }

    public static int binarySearch(int[] array, int num) {
        //非递归二分查找,
        int begin = 0;
        int end = array.length - 1;
        int mid = (begin + end) / 2;
        while (array[mid] != num) {
            if (begin > end) return -1;
            if (num > array[mid]) begin = mid + 1;
            else if (num < array[mid]) end = mid - 1;
            mid = (begin + end) / 2;
        }
        return mid;
    }
}
