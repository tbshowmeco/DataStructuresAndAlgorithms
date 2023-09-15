package com.showmeco.day0902;

import java.io.*;

/**
 * PackageName: com.showmeco.day0902
 * ClassName: Readshuzu
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/2 23:16
 * @Version: 1.0
 */
public class Readshuzu {
    public static void main(String[] args) {

        try {
            FileInputStream fileInputStream = new FileInputStream("array.txt");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            int row = dataInputStream.readInt();
            int colum = dataInputStream.readInt();
            int[][] array = new int[row][colum];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    array[i][j] = dataInputStream.readInt();
                }
            }

            dataInputStream.close();
            fileInputStream.close();


            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
