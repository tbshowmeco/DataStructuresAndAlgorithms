package com.showmeco.day0914;

/**
 * PackageName: com.showmeco.day0914
 * ClassName: KmpBaoLiPiPei
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/14 10:13
 * @Version: 1.0
 */
public class KmpBaoLiPiPei {
    public static void main(String[] args) {
        String str = "jisuanjieciajfio";
        String s = "jiecia";
        System.out.println(baoli(str, s));

    }

    public static int baoli(String str, String s) {
        //暴力求解算法,查看str中是否有s字符串
        //思路,遍历str,
        for (int i = 0; i < str.length(); i++) {
            int k = i;
            int i1 = 0;
            for (; i1 < s.length() && k < str.length(); i1++, k++) {
                if (s.charAt(i1) != str.charAt(k)) {
                    break;//说明本次遍历已经有不同的字母了!,重新开始下一次遍历
                }
            }
            //判断是否把子字符串遍历完啦!
            if (i1 == s.length()) {
                return i;
            }
        }
        //主串都已经遍历完啦,说明没有
        return -1;
    }
}
