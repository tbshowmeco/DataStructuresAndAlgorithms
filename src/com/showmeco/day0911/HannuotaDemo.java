package com.showmeco.day0911;

/**
 * PackageName: com.showmeco.day0911
 * ClassName: HannuotaDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/11 21:44
 * @Version: 1.0
 */
public class HannuotaDemo {
    public static void main(String[] args) {

        hannuota(10, 'A', 'B', 'C');
    }

    public static void hannuota(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.printf("第1块从%s->%s\n", a, c);
        } else {
            hannuota(num - 1, a, c, b);
            System.out.printf("第%d块从%s->%s\n", num, a, c);
            hannuota(num - 1, b, a, c);
        }
    }
}
