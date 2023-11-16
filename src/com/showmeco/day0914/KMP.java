package com.showmeco.day0914;

import java.util.Arrays;

/**
 * PackageName: com.showmeco.day0914
 * ClassName: KMP
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/14 13:57
 * @Version: 1.0
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(kmpSearch(str1, str2));
    }


    public static int kmpSearch(String str, String s) {
        int[] next = getNext(s);
        for (int i = 0, j = 0; i < str.length(); i++) {
            //遍历大的数组,依次比较是否相等
            if (str.charAt(i) == s.charAt(j)) {
                //说明两者相等
                j++;
            } else {
                if (j != 0) i--;
                //说明不相等,直接拿next数组比较
                j = next[j];//要排除第一个元素
            }
            if (j == s.length()) {
                //说明已经找到了
                return i + 1 - s.length();//这是算出来的首位置
            }
        }
        //否则,说明没有该子字符串,返回-1
        return -1;
    }

    public static int[] getNext(String str) {

        //得到next数组,这是最核心部分,
        int length = str.length();
        int[] result = new int[length];
        result[0] = 0;//第一个元素不匹配,肯定直接回到下一个元素对比
        for (int i = 1, j = 0; i < length; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                //此时说明此时前面有一部分是相等的,但是后面有一部分不相等,则前面这一部分求到next数组对应的下标,直接接着比较后面,
                j = result[j - 1];//这里因为第i个下标和j个下标元素不相等,那么说明j前面的j-1个元素与i前面的j-1个元素相等,直接对前面j-1个next数组的值拿到,
                //然后继续比较,要是还是不相等,任然继续循环拿到next[j-1],直到相等,或者j=0为止,这里最不好理解!
            }
            if (str.charAt(i) == str.charAt(j)) {
                //说明这里两个数相等,接着往后面找,
                j++;
            }
            result[i] = j;
        }

        //再次调整一下next数组,整体往后面移动一位

        for (int i = result.length - 1; i > 0; i--) {
            result[i] = result[i - 1];
        }
        return result;
    }

}
