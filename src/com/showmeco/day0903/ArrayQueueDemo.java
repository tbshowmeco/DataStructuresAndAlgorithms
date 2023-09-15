package com.showmeco.day0903;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Scanner;

/**
 * PackageName: com.showmeco.day0903
 * ClassName: ArrayQueueDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/3 10:58
 * @Version: 1.0
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        boolean loop = true;
        while (loop) {
            System.out.println("选择一个选项:");
            System.out.println("[a] : 添加一个数入队列");
            System.out.println("[g] : 从队列取出一个数");
            System.out.println("[h] : 取出对头元素");
            System.out.println("[look] : 查看队列中元素");
            System.out.println("[e] : 退出");
            String choose = new Scanner(System.in).next();
            switch (choose) {
                default:
                    System.out.println("重新输入");
                    break;
                case "a":
                    try {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("请输入一个整数: ");
                        int num = scanner.nextInt();
                        queue.add(num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "g":
                    try {
                        System.out.println("取出元素 " + queue.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "h":
                    try {
                        System.out.println("对头元素为: " + queue.getHead());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "look":
                    try {
                        queue.look();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    loop = false;
                    break;
            }
        }
    }
}

class ArrayQueue {

    private int maxsize;
    private int front;
    private int rear;
    private int[] array;

    ArrayQueue(int n) {//初始化
        this.maxsize = n;
        this.front = -1;
        this.rear = -1;
        this.array = new int[n];
    }

    public void add(int num) {
        if (isFull()) {
            throw new RuntimeException("队列满了，不能加数据");
        }
        rear++;
        array[rear] = num;

    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return array[++front];
    }


    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return rear == maxsize - 1;
    }


    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return array[1 + front];
    }

    public void look() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.println(array[i] + " ");
        }
    }
}
