package com.showmeco.day0904;

/**
 * PackageName: com.showmeco.day0904
 * ClassName: FindWayDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/4 22:27
 * @Version: 1.0
 */
public class FindWayDemo {
    public static void show(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int[][] map = new int[8][7];
        //设置边界
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = map[3][2] = map[4][3] = map[6][4] = map[4][5] = 1;
        show(map);
        findWay(map, 1, 1);
        System.out.println();
        show(map);
    }

    private static boolean findWay(int[][] map, int i, int j) {
        //设置终点是什么  1表示围墙,不可逾越,2表示已经走过的节点,3表示走过,走不通的节点
        if (map[6][5] == 2) {
            return true;//最终递归的终点
        } else {
            if (map[i][j] == 0) {
                //先假定map[i][j]可以走
                map[i][j] = 2;
                //下,右,上,左,依次测试是否可以走通
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else {
                    //说明一个都没有走通,
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;//说明map[i][j] =1/2/3 总之这个节点不可以走了!
            }

        }
    }
}
