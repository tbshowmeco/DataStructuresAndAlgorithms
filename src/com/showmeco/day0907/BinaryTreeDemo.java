package com.showmeco.day0907;

/**
 * PackageName: com.showmeco.day0907
 * ClassName: BinaryTreeDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/7 11:40
 * @Version: 1.0
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {

        MyBinaryTree tree = new MyBinaryTree();
        tree.add(6);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        tree.add(7);
        tree.add(16);
        tree.add(13);
        tree.add(14);
        tree.add(21);
        tree.add(18);
        tree.add(23);
        tree.add(0);

        tree.lastShow();
    }
}

class MyBinaryTree {
    private Node root = null;


//    public void showPreThread() {
//
//        //遍历线索化二叉树,这里是前序线索化
//
//        Node temp = root;
//        while (true) {
//            System.out.println(temp);
//            if (root.getLeftType() == 1) {
//                temp = temp.getLeft();
//            } else {
//
//            }
//
//        }
//
//    }

    public void preShowThread(Node preTemp, Node temp) {
        //前序遍历的线索化

        //对当前节点进行处理
        if (preTemp != null && preTemp.getRight() == null) {
            //说明当前节点应该是下一个节点,是线索
            preTemp.setRight(temp);
            preTemp.setRightType(1);
            if (temp == null) {
                //说明已经处理到了结尾,只需要处理上一个节点的右线索
                return;
            }
        }
        if (temp.getLeft() == null) {
            //说明当前节点的左指针是线索,
            temp.setLeft(preTemp);
            temp.setLeftType(1);
        }

        //开始递归处理下一个节点
        preShowThread(temp, temp.getLeft());
        preShowThread(temp, temp.getRight());


    }

    public void preShow() {
        this.root.preShow();
    }

    public void middleShow() {
        this.root.middleShow();
    }

    public void lastShow() {
        this.root.lastShow();
    }

    public void add(int num) {
        //加入

        Node node = new Node(num);
        //先排除加入的是第一个元素
        if (root == null) {
            root = node;
            return;
        }
        Node temp = root;
        while (true) {
            if (temp.getNum() == num) {
                throw new RuntimeException("二叉树不许有相同元素!");
            } else if (num < temp.getNum()) {
                if (temp.getLeft() == null) {
                    temp.setLeft(node);
                    return;
                }
                temp = temp.getLeft();
            } else {
                if (temp.getRight() == null) {
                    temp.setRight(node);
                    return;
                }
                temp = temp.getRight();
            }
        }
    }

    public int get(int num) {
        //查找
        Node temp = root;
        while (true) {
            if (num == temp.getNum()) {
                return temp.getNum();
            } else if (num < temp.getNum()) {
                if (temp.getLeft() == null) {
                    throw new RuntimeException("没有这个数!");
                }
                temp = temp.getLeft();
            } else {
                if (temp.getRight() == null) {
                    throw new RuntimeException("没有这个数!");
                }
                temp = temp.getRight();
            }
        }
    }

    public void del(int num) {
        if (isEmpty()) {
            throw new RuntimeException("二叉树为空!!");
        }

        //先排除根节点
        if (root.getNum() == num) {
            root = null;
            return;
        }
        Node temp = root;
        while (true) {
            if (num < temp.getNum()) {
                if (temp.getLeft() == null) {
                    throw new RuntimeException("没有这个数");
                }
                if (temp.getLeft().getNum() == num) {
                    temp.setLeft(null);
                    return;
                }
                temp = temp.getLeft();
            } else {
                if (temp.getRight() == null) {
                    throw new RuntimeException("没有这个数");
                }
                if (temp.getRight().getNum() == num) {
                    temp.setRight(null);
                    return;
                }
                temp = temp.getRight();
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}

class Node {
    //二叉树的每一个节点
    private Node left;
    private Node Right;
    //表示左右指针的类型,0表示节点,1表示线索
    private int leftType;
    private int rightType;


    private int num;

    Node(int num) {
        this.num = num;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return Right;
    }

    public void setRight(Node right) {
        Right = right;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Node{" + ", num=" + num + "left=" + left + ", Right=" + Right + '}';
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }


    public void preShow() {
        //前序遍历
        System.out.println(this.toString());
        if (this.getLeft() != null) {
            this.getLeft().preShow();
        }
        if (this.getRight() != null) {
            this.Right.preShow();
        }
    }


    public void middleShow() {
        if (this.getLeft() != null) {
            this.getLeft().middleShow();
        }
        System.out.println(this.toString());
        if (this.Right != null) {
            this.Right.middleShow();
        }
    }

    public void lastShow() {
        if (this.getLeft() != null) {
            this.getLeft().lastShow();
        }
        if (this.getRight() != null) {
            this.getRight().lastShow();
        }
        System.out.println(this.toString());
    }
}
