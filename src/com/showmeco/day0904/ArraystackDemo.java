package com.showmeco.day0904;

import java.util.Scanner;

/**
 * PackageName: com.showmeco.day0904
 * ClassName: ArraystackDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/4 12:17
 * @Version: 1.0
 */
public class ArraystackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        boolean loop = true;
        while (loop) {
            System.out.println("请输入选择:");
            System.out.printf("[a] : push\n");
            System.out.printf("[g] : pop\n");
            System.out.printf("[show] : 遍历栈\n");
            System.out.printf("[e] : 退出\n");
            Scanner scanner = new Scanner(System.in);
            String choose = scanner.next();

            switch (choose) {
                default:
                    System.out.println("重新输入:");
                    break;
                case "a":
                    try {
                        System.out.println("请输入一个整数:");
                        stack.push(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "g":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show":
                    try {
                        stack.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "e":
                    scanner.close();
                    loop = false;


            }
        }

    }
}

class ArrayStack {

    private int[] array;
    private int maxSize;
    private int stackTop = -1;


    ArrayStack(int num) {
        maxSize = num;
        array = new int[num];
    }


    public boolean isEmpty() {
        return this.stackTop == -1;
    }

    public boolean isFull() {
        return this.stackTop == maxSize - 1;
    }

    public int pop() {
        if (isEmpty())
            throw new RuntimeException("栈为空,不可取出!");
        return array[stackTop--];
    }

    public void push(int num) {
        if (isFull())
            throw new RuntimeException("栈已经满了,不可以添加!!");
        array[++stackTop] = num;
    }

    public void show() {
        if (isEmpty())
            throw new RuntimeException("栈为空,不可遍历!");
        int temp = stackTop;
        while (temp != -1) {
            System.out.print(array[temp--] + "->");
        }
    }
}