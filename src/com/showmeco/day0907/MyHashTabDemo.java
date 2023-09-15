package com.showmeco.day0907;

import java.util.List;

/**
 * PackageName: com.showmeco.day0907
 * ClassName: MyHashTab
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/7 9:40
 * @Version: 1.0
 */
public class MyHashTabDemo {
    public static void main(String[] args) {

        MyHashTab tab = new MyHashTab();
        tab.add(new MyNode(5, "jjj"));
        tab.add(new MyNode(1, "jjj"));
        tab.add(new MyNode(3, "jjj"));
        tab.add(new MyNode(17, "jjj"));
        tab.add(new MyNode(45, "jjj"));
        tab.add(new MyNode(23, "jjj"));
        tab.add(new MyNode(32, "jjj"));
        tab.add(new MyNode(33, "jjj"));

        tab.del(new MyNode(1,"jjj"));

        System.out.println(tab.get(17));

    }
}

class MyHashTab {
    MyNodeList[] list = new MyNodeList[16];

    MyHashTab() {
        for (int i = 0; i < list.length; i++) {
            list[i] = new MyNodeList();
        }
    }

    public void add(MyNode node) {
        int num = hashFunction(node.num);
        list[num].add(node);
    }

    public void del(MyNode node) {
        list[hashFunction(node.num)].del(node);
    }

    public void list() {
        //遍历所有的hashtab中的数据
        for (int i = 0; i < list.length; i++) {
            list[i].list();
        }
    }

    public MyNode get(int num) {
        try {
            return list[hashFunction(num)].get(num);
        } catch (Exception e) {
            System.out.println("没有这个数!");
        }
        return null;
    }

    public int hashFunction(int num) {
        return num % 16;
    }
}

class MyNodeList {

    private MyNode head = null;
    private MyNode last = null;

    public void add(MyNode node) {
        //有序添加node

        //排除第一个元素的可能
        if (head == null) {//链表为空的时候,直接插入
            head = node;
            last = node;
            return;
        } else if (head.num > node.num) {
            node.next = head;
            head = node;
            return;
        }
        MyNode temp = head.next;
        MyNode preTemp = head;
        //找到插入的位置
        while (temp != null) {
            if (temp.num > node.num) {
                node.next = temp;
                preTemp.next = node;
                return;
            } else {
                preTemp = temp;
                temp = temp.next;
            }
        }
        //到这里说明已经到了链表末尾
        last.next = node;
        last = node;

    }

    public void del(MyNode node) {
        int num = node.num;

        //首先排除第一个元素就是要找的元素
        if (head.num == num) {
            head = head.next;
            return;
        }
        //说明第一个节点不是要删除的节点
        MyNode tempNode = head.next;
        MyNode pre = head;
        while (tempNode.num != num && tempNode != null) {
            pre = tempNode;
            tempNode = tempNode.next;
        }
        //找到了该节点,直接删除
        if (tempNode != null) {
            //找到节点,删除
            pre.next = tempNode.next;
        } else {
            throw new RuntimeException("没有这个数");
        }

    }

    public boolean isEmpty() {
        return head == null;
    }

    public MyNode get(int num) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空!");
        }
        MyNode node = head;
        while (node != null) {
            if (node.num == num) {
                return node;
            }
            node = node.next;
        }
        throw new RuntimeException("链表中没有这个数据");

    }

    public void list() {
        //遍历所有元素
        MyNode node = head;
        while (node != null) {
            System.out.print(node.num + "->");
            node = node.next;
        }
        System.out.println();
    }
}

class MyNode {
    public int num;
    public String name;
    public MyNode next;

    MyNode(int num, String name) {
        this.num = num;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}