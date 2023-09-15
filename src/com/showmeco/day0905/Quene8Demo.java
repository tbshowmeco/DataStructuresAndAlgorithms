package com.showmeco.day0905;

import java.util.spi.CalendarNameProvider;

/**
 * PackageName: com.showmeco.day0905
 * ClassName: Quene8Demo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/5 9:43
 * @Version: 1.0
 */
public class Quene8Demo {
    private static int queneNum = 8;

    private static int num = 0;
    private static int calNum = 0;
    private static int[] array = new int[queneNum];

    public static void main(String[] args) {
        System.out.println("解法如下:");
        long begin = System.currentTimeMillis();
        set(0);
        long end = System.currentTimeMillis();


        System.out.printf("一共有%d种解法,一共判断了%d次是否冲突,耗时" + (end - begin) + "毫秒", num, calNum);
    }

    public static void set(int n) {
        //放置第n个皇后
        if (n == queneNum) {
            //说明已经递归到了最后一个皇后,已经成功
            print();
            num++;
            return;
        }

        //放置第n个皇后
        for (int i = 0; i < queneNum; i++) {
            //先放在本行的第一个位置
            array[n] = i;

            if (check(n)) {
                //检查放置在这里是否符合(需要检查前n个与它是否冲突)
                //检查通过,放置下一行
                set(n + 1);
                //如果set(n+1)返回了,则继续回溯,好比a调用b,b返回了,a肯定还是继续执行....
            }
            //如果检测冲突了,则继续执行下一个位置,继续判断是否冲突

            //自动不断回溯,找到所有的解!

        }

    }

    private static boolean check(int n) {
        calNum++;
        //放置第n个位置,检查之前的放置时候合理
        for (int i = 0; i < n; i++) {
            //循环检查每一行与此行是否有冲突
            if (array[i] == array[n] //同一列
                    || Math.abs(n - i) == Math.abs(array[n] - array[i])//同一斜线
            ) {
                //array的下标表示行,值表示列,如果在同一行或者在同一条斜线上,则不能通过
                //判断斜线是使用横坐标之差与纵坐标之差如果相等,则说明在同一条斜线删(等腰直角三角形)
                return false;
            }

        }
        return true;
    }

    private static void print() {
        for (int i = 0; i < queneNum; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

