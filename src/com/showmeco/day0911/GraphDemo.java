package com.showmeco.day0911;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PackageName: com.showmeco.day0911
 * ClassName: GraphDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/11 17:22
 * @Version: 1.0
 */
public class GraphDemo {
    public static void main(String[] args) {

        Graph graph = new Graph(8);
        String[] str = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < str.length; i++) {
            graph.addNode(str[i]);
        }

        graph.setGraph(0, 1, 1);
        graph.setGraph(0, 2, 1);
        graph.setGraph(1, 3, 1);
        graph.setGraph(1, 4, 1);
        graph.setGraph(3, 7, 1);
        graph.setGraph(4, 7, 1);
        graph.setGraph(2, 5, 1);
        graph.setGraph(2, 6, 1);
        graph.setGraph(5, 6, 1);

        graph.showJuZhen();

        graph.dfs();

        System.out.println("*************");
        graph.bfs();
    }
}

class Graph {
    //用string[]表示所有的图中的节点,以便以后续遍历
    public ArrayList<String> nodes;


    //用一个二维数组表示图的邻接矩阵
    public int[][] graph;


    //初始化数组
    Graph(int n) {
        nodes = new ArrayList<String>(n);
        graph = new int[n][n];
    }

    public void addNode(String node) {
        nodes.add(node);
    }

    public void setGraph(int i, int j, int num) {
        //这是一个无向图,一设置就是双向的!!
        graph[i][j] = num;
        graph[j][i] = num;
    }


    public void showJuZhen() {
        for (int i = 0; i < this.graph.length; i++) {
            for (int i1 = 0; i1 < this.graph[i].length; i1++) {
                System.out.print(this.graph[i][i1] + " ");
            }
            System.out.println();
        }
    }


    public void dfs() {
        //深度优先遍历
        //思路 就是使用递归,先从第一个节点可是遍历,找到它的第一个节点,继续往下去遍历,要是没有,则回溯回去,又遍历下一个

        //应该是每一次遍历都有一个数组,记录遍历到哪些地方了,哪些节点已经在本次遍历中遍历过了
        boolean[] isShow = new boolean[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            //循环递归的从第一个节点开始去遍历图,,先从第一个节点开始,往下遍历和它相邻的节点,直到遍历完,再从第二个节点开始....
            if (isShow[i] != true) dfs(isShow, i);
        }
    }

    private void dfs(boolean[] isShow, int i) {
        System.out.print(nodes.get(i) + "->");
        //重载一个方法,实现对图的遍历,深度优先
        ///先把当前节点置为已经遍历了!
        isShow[i] = true;
        int j = getFirstNode(i);//从邻接矩阵中得到当前节点的第一个相连节点的索引
        while (j != -1) {
            if (isShow[j] != true) {
                //说明找到的这个节点还没有被访问
                //说明找到了该节点的第一个相邻的节点的索引
                dfs(isShow, j);
            }
            //这里不要写else,因为遍历完了该节点,返回来的时候,还要继续往下遍历它的第二个相邻节点!
            //说明i节点的第一个相邻节点已经被访问过了,继续找下一个节点
            j = getNextNode(i, j);
        }//等于-1,说明该节点没有相邻的节点,或者全部已经被访问了!则回溯到上一层...
    }

    private int getNextNode(int index, int j) {
        for (int i = j + 1; i < graph[0].length; i++) {
            //从第一个元素下一个开始遍历,得到下一个相邻元素的下标
            if (graph[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private int getFirstNode(int index) {
        //从邻接矩阵中拿到index节点相连的第一个节点的索引
        for (int i = 0; i < graph[0].length; i++) {
            if (graph[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void bfs() {
        //广度优先遍历图
        //思路: 优先把一个节点的所有相邻节点遍历完,再遍历下一个节点

        Queue<Integer> queue = new PriorityQueue<>();

        //得到一个数组,标记哪个节点是已经访问了的
        boolean[] isShow = new boolean[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            if (isShow[i] != true) bfs(queue, isShow, i);///那么久继续广度优先遍历该节点
        }
    }

    private void bfs(Queue<Integer> queue, boolean[] isShow, int index) {

        if (isShow[index] != true) {
            //如果当前节点没有遍历,那么遍历当前节点
            System.out.print(nodes.get(index) + "->");
            isShow[index] = true;
        }

        //广度遍历当前节点的相邻节点
        for (int j = 0; j < nodes.size(); j++) {
            if (graph[index][j] > 0 && isShow[j] != true) {
                isShow[j] = true;
                queue.add(j);
                System.out.print(nodes.get(j) + "->");
            }
        }
        //直到当前节点的所有相邻节点遍历完,再取出对头节点遍历
        if (!queue.isEmpty()) bfs(queue, isShow, queue.poll());
    }

}
