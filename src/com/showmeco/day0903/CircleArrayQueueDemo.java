package com.showmeco.day0903;

import com.sun.prism.shader.FillRoundRect_LinearGradient_PAD_AlphaTest_Loader;

import java.util.Scanner;

/**
 * PackageName: com.showmeco.day0903
 * ClassName: CircleArrayQueueDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/3 14:50
 * @Version: 1.0
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
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

class CircleArrayQueue {
    private int[] array;
    private int front;
    private int rear;
    private int maxSize;

    CircleArrayQueue(int num) {
        maxSize = num;
        array = new int[num];
        front = 0;
        rear = 0;
        //这一次front 指向当前对头元素 rear指向当前队尾元素的下一个元素

    }


    public boolean isEmptyh() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public int get() {
        if (isEmptyh()) {
            throw new RuntimeException("队列为空,无法取出数据!");
        }
        return array[front++ % maxSize];
    }

    public void add(int num) {
        if (isFull()) {
            throw new RuntimeException("队列已经满了,不能再加入数据!!");
        }
        array[rear++ % maxSize] = num;
    }

    public int getNum() {
        return (rear + maxSize - front) % maxSize;
    }

    public void look() {
        if (isEmptyh()) {
            throw new RuntimeException("队列为空,无法取出数据!");
        }
        for (int i = front; i < front   + getNum(); i++) {
            System.out.println(array[i % maxSize] + " ");
        }
    }

    public int getHead() {
        return array[front];
    }

}
