package com.showmeco.day0906.search;

/**
 * PackageName: com.showmeco.day0905
 * ClassName: day0906
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/6 16:42
 * @Version: 1.0
 */
public class BinarySearch {
    public static void main(String[] args) {

        int[] array = {0,1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearch(array, 0, array.length - 1,  3));
    }

    public static int binarySearch(int[] array, int l, int r, int num) {
        //思路,使用递归查找

        if (l > r) return -1;//说明已经移出了数组,任然没有找到
        //递归终止条件
        int mid = (l + r) / 2;
        double epsilon = 1e-3; // 精度范围
        if (Math.abs(array[mid] - num) < epsilon) {
            // 数字在给定精度范围内相等
            return mid;
        } else if (array[mid] > num) {
            //向左递归
            return binarySearch(array, l, mid - 1, num);
        } else {
            return binarySearch(array, mid + 1, r, num);
        }
    }
}
