package com.showmeco.day0903;

import org.junit.Test;

import java.util.Scanner;

/**
 * PackageName: com.showmeco.day0903
 * ClassName: SingleLinkedList
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/3 16:21
 * @Version: 1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        SingleLinkedList list = new SingleLinkedList();
        boolean loop = true;
        while (loop) {
            System.out.println("选择一个选项:");
            System.out.println("[a] : 添加一个数入链表");
            System.out.println("[d] : 从链表中删除一个数");
            System.out.println("[u] : 从链表更新一个数");
            System.out.println("[look] : 查看链表中所有元素");
            System.out.println("[e] : 退出");
            System.out.println("[r] : 返转链表");
            System.out.println("[rp] : 逆序打印链表");
            Scanner scanner = new Scanner(System.in);
            String choose = scanner.next();
            switch (choose) {
                case "rp":
                    //逆序打印链表
                    list.reversePrint();
                    break;
                case "r":
                    //返转链表
                    list.reverseLinkList().list();
                    break;
                default:
                    System.out.println("重新输入");
                    break;
                case "a":
                    try {
                        System.out.println("请输入一个整数: ");
                        int num = scanner.nextInt();
                        list.addByOrder(new Node(num));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "d":
                    try {
                        System.out.println("请输入一个整数: ");
                        int num = scanner.nextInt();
                        list.delete(num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "u":
                    try {
                        System.out.println("请输入旧数: ");
                        int num = scanner.nextInt();

                        System.out.println("请输入新数: ");
                        int newNum = scanner.nextInt();
                        list.update(num, newNum);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "look":
                    try {
                        list.list();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    loop = false;
                    scanner.close();
                    break;
            }
        }

    }

}


class SingleLinkedList {
    //头节点
    private Node head = new Node(0);

    //last节点指定
    private Node lastNode = head;
    private Node preNode = head;


    public SingleLinkedList reverseLinkList() {
        Node head = this.gethead();
        SingleLinkedList result = new SingleLinkedList();
        Node resulthead = result.gethead();
        //循环旧的单链表,生成返转的单链表
        Node node = head.next;
        while (node != null) {
            Node newNode = node.next;
            //插入新的单链表中
            node.next = resulthead.next;
            resulthead.next = node;
            node = newNode;
        }
        return result;
    }

    public void reversePrint() {
        Node node = this.gethead().next;
        rprint(node);

    }

    private void rprint(Node node) {
        if (node.next != null) {
            //说明有下一个节点,则先打印前面的
            rprint(node.next);
        } else {
            System.out.println(node.num + "->");
        }
    }

    public Node gethead() {
        return head;
    }

    public void addByOrder(Node node) {
        Node tempNode = head.next;
        while (tempNode != null) {
            if (tempNode.num == node.num) {
                throw new RuntimeException("该节点已经存在,不可重复添加");
            } else if (tempNode.num > node.num) {//说明找到了添加位置,直接添加即可
                node.next = preNode.next;
                preNode.next = node;
                lastNode = node;//每次添加元素要更改最后一个node
                return;
            } else {
                preNode = tempNode;
                tempNode = tempNode.next;
            }
        }
        //跳出循环,说明已经找到链表尾部,直接添加到尾部位置
        lastNode.next = node;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public void add(Node node) {//无序的插入进链表中
        lastNode.next = node;
        lastNode = node;
        //这只是只管往里面加,不管顺序
    }


    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("链表为空!!!");
        }
        //遍历所有的节点
        Node node = head.next;
        while (node != null) {
            System.out.printf(" %d -> ", node.num);
            node = node.next;
        }
        System.out.println();
    }

    public void delete(int num) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空!!!");
        }
        Node node = head.next;

        while (node != null) {
            if (node.num == num) {
                preNode.next = node.next;
                return;//删除第一个该节点
            } else {
                preNode = node;
            }
            node = node.next;
        }
    }


    public void update(int num, int newNum) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空!!!");
        }
        //更新一个节点

        Node node = head.next;

        preNode = head;
        while (node != null) {
            if (node.num == num) {
                node.num = newNum;
                return;//更新第一个该节点
            } else {
                preNode = node;
            }
            node = node.next;
        }

    }

}

class Node {
    public int num;
    public Node next;

    Node(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Node{" + "num=" + num + ", next=" + next + '}';
    }
}

