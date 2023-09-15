package com.showmeco.day0904;

import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * PackageName: com.showmeco.day0904
 * ClassName: yusefuCircle
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/4 11:34
 * @Version: 1.0
 */
public class yusefuCircle {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(5);
        list.show();
        System.out.println();
        list.yusefu(2);
    }
}

class CircleSingleLinkedList {

    //定义环形单链表
    private CirCleNode first = new CirCleNode(1);

    private CirCleNode last = first;

    public void add(int num) {
        last.next = first;//独立成环
        for (int i = 2; i <= num; i++) {
            CirCleNode node = new CirCleNode(i);
            //加入剩下的节点进环
            node.next = last.next;
            last.next = node;
            last = node;
        }
    }

    public void yusefu(int n) {
        //思路,使用循环遍历,直到只有一个节点剩余,这里要使用一个pre节点,用于删除temp节点

        CirCleNode preTemp = null;
        CirCleNode temp = first;
        getPreTemp(preTemp);//把preTemp初始化为最后一个节点
        while (temp.next != temp) {
            ///如果temp.next==temp,说明只剩下temp一个节点了!!!
            for (int i = 0; i < n - 1; i++) {//con自己开始数,只需要移动n-1次
                preTemp = temp;
                temp = temp.next;
            }
            System.out.print(temp.num + "->");
            //删除这个节点
            preTemp.next = temp.next;
            temp = temp.next;///还要将temp往后移动,继续约瑟夫环
        }

        System.out.println();
        System.out.println("最后剩下的是:" + temp.num);
    }

    private void getPreTemp(CirCleNode preTemp) {
        CirCleNode node = first;
        while (node.next != first) {
            node = node.next;
        }
        preTemp = node;
    }

    public void show() {
        CirCleNode node = first;
        do {
            //没有到最后一个节点
            System.out.print(node.num + "->");
            node = node.next;
        } while (node != first);
    }


}


class CirCleNode {
    public int num;

    public CirCleNode next;

    CirCleNode(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CirCleNode{" + "num=" + num + ", next=" + next + '}';
    }
}
