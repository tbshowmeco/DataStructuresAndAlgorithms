package com.showmeco.day0903;

import java.awt.geom.QuadCurve2D;

/**
 * PackageName: com.showmeco.day0903
 * ClassName: DoubleLinkedList
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/3 22:18
 * @Version: 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {


        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new DoubleNode(22));
        list.addByOrder(new DoubleNode(2));
        list.addByOrder(new DoubleNode(32));
        list.addByOrder(new DoubleNode(12));
        list.addByOrder(new DoubleNode(42));

        list.look();
    }
}

class DoubleLinkedList {
    //头结点
    private DoubleNode head = new DoubleNode(0);


    public void addByOrder(DoubleNode node) {
        DoubleNode tempNode = head.next;
        DoubleNode last = head;
        while (tempNode != null) {
            if (tempNode.num > last.num)
                last = tempNode;//因为是按照顺序来插入,所以最大的那个数就是最后的节点!
            if (tempNode.num == node.num) {
                throw new RuntimeException("该节点已经存在!");
            } else if (tempNode.num > node.num) {
                //说明应该插入该节点中  先连左边,再连右边
                tempNode.pre.next = node;
                node.pre = tempNode.pre;
                node.next = tempNode;
                tempNode.pre = node;

                //说明已经插入其中,直接返回
                return;
            } else {
                tempNode = tempNode.next;
            }
        }
        //到达这里,说明到达了末尾,直接插入末尾

        last.next = node;
        node.pre = last;
        last = node;

    }

    public DoubleNode getHead() {
        return head;
    }

    public void del(int num) {
        DoubleNode node = head.next;
        while (node != null) {
            if (node.num == num) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                return;
            } else {
                node = node.next;
            }
        }
    }

    public void update(int num, int newNum) {
        DoubleNode node = head.next;
        while (node != null) {
            if (node.num == num) {
                node.num = newNum;
                return;
            } else {
                node = node.next;
            }
        }
    }

    public void look() {
        DoubleNode node = head.next;
        while (node != null) {
            System.out.print(node.num + "->");
            node = node.next;
        }
    }

}


class DoubleNode {
    public int num;
    public DoubleNode pre;
    public DoubleNode next;

    DoubleNode(int num) {
        this.num = num;
    }
}
