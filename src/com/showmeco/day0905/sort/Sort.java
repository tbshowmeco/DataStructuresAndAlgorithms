package com.showmeco.day0905.sort;

import org.junit.Test;

import java.awt.geom.QuadCurve2D;
import java.io.CharArrayReader;
import java.util.Arrays;
import java.util.Random;
import java.util.jar.JarEntry;

/**
 * PackageName: com.showmeco.day0905
 * ClassName: BubbleSort
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/5 11:12
 * @Version: 1.0
 */
public class Sort {
    public static void main(String[] args) {
        int[] test = new int[8000000];
        for (int i = 0; i < test.length; i++) {
            test[i] = (int) (Math.random() * 8000001);
        }

        int[] array = {32, 31, 33, 23, 34, 56, 13, 3, 6, 1, 7, 8, 2, 44};
        int[] array1 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};


        print(array1);
//        mergeSort(array1, 0, array1.length - 1, new int[array1.length]);
        //        quickSort(array1, 0, array1.length - 1);
         heapSort(array1);
        print(array1);

        System.out.println("****************************");

        int[] temp = new int[test.length];
        long begin = System.currentTimeMillis();
        heapSort(test);
        //        quickSort(test, 0, test.length - 1);
        long end = System.currentTimeMillis();

        System.out.printf("参考排序耗时:%d毫秒", end - begin);

    }

    public static void heapSort(int[] array) {
        //堆排序
        //思想: 堆,每次得到一个最大(小)的数,再把这个数与数组末尾的数进行交换即可实现排序


        //先从最后一个非叶子节点开始,往前一次调整树,实现最终整颗树都是大顶堆树,此时根节点就是最大的那颗树!!
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            //数组长度/2-1  是第一个非叶子节点的下标
            adjustTree(array, i, array.length);
        }

        int temp = 0;
        for (int i = array.length - 1; i > 0; i--) {
            //只需要确定前n-1个数,就可以确定最后一个数,实现有序

            temp = array[i];
            array[i] = array[0];
            array[0] = temp;

            adjustTree(array, 0, i);

        }

    }

    /**
     * @param array  待调整的树,(利用数组顺序存储二叉树,这里是堆排序,肯定是完全二叉树!)
     * @param i      根节点
     * @param length 需要调整的数组的元素个数
     */
    private static void adjustTree(int[] array, int i, int length) {
        //调整树为大顶堆

        int temp = array[i];

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                //说明以下标i为根节点的左子节点的值小于右子节点的值,让k指向大的数(这里即是右子节点)
                k++;
            }
            //再拿子节点中大的数与根节点进行比较,如果大,则交换

            if (array[k] > temp) {
                array[i] = array[k];
                i = k;//这里使用i不停的去标记本次调整后temp暂时应该放的位置,就避免每次调整都去设置这个数,
                //使用i不停的去往下传递,i的位置就是temp最终应该在的位置,
            } else {
                //说明以i为根节点的树本就是大顶堆,不需要更改,那么此时直接退出,不再左无用的运算
                break;
            }
        }
        //最后,循环结束,temp应该在的位置就是i
        array[i] = temp;
    }

    public static int[] countSort(int[] array) {
        //计数排序
        //思想: 对原始数组进行遍历处理,得到一个计数数组,计数数组的下标要涵盖原始数组中的最大值, 用于统计每个数出现的次数
        //然后逆序遍历原数组,得到排好序的数组


        //找到最大的值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        //new 计数数组
        int[] count = new int[max + 1];


        for (int i = 0; i < array.length; i++) {
            count[array[i]] += 1;
        }

        //计算每一个数应该出现在什么位置
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        ///逆序得到原数组
        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int num = array[i];
            result[count[num] - 1] = num;
            count[num]--;

        }

        return result;
    }

    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        //归并排序
        //思路: 不断递归的去递归左边部分,左边部分分为了两个数的数组的时候,再次递归则返回,此时又往右边回溯,,因为左右两边都是
        //在只有一个元素的才会终止递归,所以,回溯完右边之后,立即又往下执行,则执行归并,

        //本质上,没有分解到只有一个元素的时候,即在有两个元素的是时候,就对该数组进行了归并

        if (left < right) {
            int mid = (left + right) / 2;
            //继续递归分解左边
            mergeSort(array, left, mid, temp);
            //继续递归分解右边
            mergeSort(array, mid + 1, right, temp);

            //分解完左边,回溯到第一个分解右边执行完,则立即执行合并,此时其实是对一个二个数的数据进行排序
            merge(array, left, mid, right, temp);

        }
    }

    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        int num = 0;
        while (i <= mid && j <= right) {
            int leftNum = array[i];
            int rightNum = array[j];
            if (leftNum <= rightNum) {
                temp[t++] = leftNum;
                i++;
                num++;
            } else {
                temp[t++] = rightNum;
                j++;
                num++;
            }
        }
        //合并完了,看还有哪个数组没有合并完,依次往后面合并
        while (i <= mid) {
            temp[t++] = array[i++];
            num++;
        }
        while (j <= right) {
            temp[t++] = array[j++];
            num++;
        }

        //真正合并完了,把temp中的数转移到真实数组中,

        int templeft = left;
        for (int k = 0; k < num; k++) {
            //num表示temp数组中本次合并有多少个数
            array[templeft++] = temp[k];
        }

    }

    public static void quickSort(int[] array, int left, int right) {
        //快速排序
        //思路,使用递归,时间换空间的方法,
        //选择一个基准数字,把大于它的数放在右边,小于它的数放在左边,如此再递归左右两边数组

        //基准数字
        int l = left;
        int r = right;
        int mid = (left + right) / 2;
        int pivot = array[mid];
        while (true) {
            //循环找到小于baseNum的数,放到左边,大于baseNum的数,放到右边

            while (array[l] < pivot) l++;//找到左边的一个大于等于baseNum的数
            while (array[r] > pivot) r--;
            if (l >= r) break;//最后终止的时候有可能l == r 也有可能l>r
            exchange(array, l, r);//交换左右两边的数


            //下面这两步操作很重要,因为可能遇见array[l] = array[r] = pivot的情况,这种情况也需要改变r和l的值,否则会出现
            //死循环,就是最终只需要保证的是左边部分的数全是小于等于pivot的,右边全是大于等于pivot的即可,

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if (array[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (array[r] == pivot) {
                l += 1;
            }
        }
        //退出循环的时候,l==r

        //再递归左边与右边
        if (left < l - 1) quickSort(array, left, l - 1);
        if (r + 1 < right) quickSort(array, r + 1, right);
    }

    public static void shellSort(int[] array) {

//注意数组中使用下标来标注数字,会导致出错,因为前后都是使用的下标,并不能临时保存数字!!!!!


        //希尔排序
        //思想,再插入排序的基础上再升级,升级的地方在于插入排序当末尾的数很小的时候就要将前面的多个元素后移,这样很浪费时间,希尔排序解决这个问题,
        //将数组分为多个小组,每个小组分别排序,多次处理....
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //gap表示同组的两个数之间的隔离个数
            for (int i = gap; i < array.length; i++) {
                //表示要进行多少次插入
                int num = array[i];//表述这次要插入的数字的下标
                for (int j = i - gap; j >= 0; j -= gap) {
                    //这个循环用于向前面数组插入数据,
                    if (array[j] <= num) {
                        //说明是在这个位置插入
                        array[j + gap] = num;
                        break;//num已经插入,则直接进入下一个num的插入
                    } else {
                        //说明不是在这里插入,应该往前找,
                        array[j + gap] = array[j];
                        if (j - gap <= 0) {
                            //说明已经到数组头部了,直接插入即可
                            array[j] = num;
                        }
                    }
                }
            }
        }
    }

    public static void InsertSort(int[] array) {
        //插入排序
        //思路: 把数组划分一下,划分为前半部分和后半部分,用后半部分重新插入前半部分中   避免又开辟一个数组
        for (int i = 0; i < array.length - 1; i++) {
            //从i处开始划分,每次取出i+1 的元素插入到前面的子数组中(从后往前插入)
            int num = array[i + 1];
            for (int j = i; j > -1; j--) {
                if (array[j] <= num) {
                    //说明这里是插入的位置,直接插入即可
                    array[j + 1] = num;
                    break;//说明已经找到本次元素插入位置,直接找下一个元素
                } else {
                    //说明这里不是插入的位置,继续往前走
                    array[j + 1] = array[j];//把前一个位置空处来,不停的往后移
                    if (j == 0) {
                        //说明应该插入到数组头中
                        array[0] = num;
                    }
                }
            }
        }
    }

    public static void selectSort(int[] array) {
        //选择排序
        //思路:还是一次一次遍历,那么总共需要n-1次遍历,每次找到最小(大)的数,与第一个数交换,每一次遍历之前已经处理了的则不需要处理
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {//这个for的作用就是找到最小的数
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            //交换这两个数
            exchange(array, i, min);
        }
    }

    private static void bubbleSort(int[] array) {
        //思路是一次一次的遍历数组,每次遍历都要比上一次少遍历一个数,因为这个数在在上一次遍历中已经排好序,不需要再比较
        //优化就是,如果某一次遍历一个顺序都没有改变,则说明这个数组已经排好序了!

        for (int k = 0; k < array.length - 1; k++) {
            int flag = 0;
            for (int i = 0; i < array.length - 1 - k; i++) {
                if (array[i] > array[i + 1]) {
                    flag = 1;//说明本次循环已经发生了交换
                    exchange(array, i, i + 1);
                }
            }
            if (flag == 0) return;//说明有一轮比较中一次都没有交换,已经排好序了!
        }
    }

    private static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

}
