package com.showmeco.day0902;

import java.io.*;

/**
 * PackageName: com.showmeco.day0902
 * ClassName: XiShuJuZhen
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/2 22:45
 * @Version: 1.0
 */
public class XiShuJuZhen {

    public void soutJuZhen(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(" " + array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        int[][] yuanjuzhen = new int[6][6];
        int[][] xishujuzhen = new int[3][3];


        yuanjuzhen[2][2] = 1;
        yuanjuzhen[3][3] = 2;

        xishujuzhen[0][0] = 6;
        xishujuzhen[0][1] = 6;
        xishujuzhen[0][2] = 2;

        xishujuzhen[1][0] = 2;
        xishujuzhen[1][1] = 2;
        xishujuzhen[1][2] = 1;

        xishujuzhen[2][0] = 3;
        xishujuzhen[2][1] = 3;
        xishujuzhen[2][2] = 2;


            FileWriter writer = new FileWriter("array1.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // 写入数组的行数和列数
            int rows = xishujuzhen.length;
            int columns = xishujuzhen[0].length;
            bufferedWriter.write(String.valueOf(rows));
            bufferedWriter.write(String.valueOf(columns));

            // 逐个写入数组中的元素
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    bufferedWriter.write(String.valueOf(xishujuzhen[i][j]));
                    bufferedWriter.write(" ");
                }
                bufferedWriter.newLine();
            }

            // 关闭流
            bufferedWriter.close();
            writer.close();
            System.out.println("数组已成功写入文件。");


        XiShuJuZhen xiShuJuZhen = new XiShuJuZhen();
        xiShuJuZhen.soutJuZhen(yuanjuzhen);
        xiShuJuZhen.soutJuZhen(xishujuzhen);


    }
}
