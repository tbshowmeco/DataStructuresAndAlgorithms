package com.showmeco.day0904;

/**
 * PackageName: com.showmeco.day0904
 * ClassName: StackCalculateDemo
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/4 14:42
 * @Version: 1.0
 */
public class StackCalculateDemo {
    public static void main(String[] args) {

        String expression = "15+2*4-8+12-2-3";

        Stack numStack = new Stack(10);
        Stack oprStack = new Stack(10);

        int sum = 0;
        //开始遍历表达式,处理

        for (int i = 0; i < expression.length(); i++) {
            char eachChar = expression.charAt(i);
            //假设现在的表达式肯定是正确的
            if (!isOpr(eachChar)) {
                //这里涉及到多位数的运算,一直往后去,取到不再是数字为止,
                //减一个48是因为char'0'所对应的ascii码是48
                int num = eachChar - 48;
                sum = sum * 10 + num;
                if (i + 1 == expression.length() || isOpr(expression.charAt(i + 1))) {
                    //说明下一个数是操作符了或者已经没有下一个数了,应该把这个数字入栈
                    numStack.push(sum);
                    sum = 0;///重置sum
                }
            } else {//说明是操作符
                //对比操作符栈的栈顶元素,要是优先级大于它了,那么就入栈,否则,取出栈顶元素与两个操作数,做运算后再将当前操作符入栈,.....
                if (oprStack.isEmpty()) {
                    //为空,直接入栈,
                    oprStack.push(eachChar);
                    continue;
                }

                //不为空
                int topOpryxj = getPriority((char) oprStack.getTopNum());//只是得到栈顶元素的优先级
                int nowyxj = getPriority(eachChar);//当前运算符的优先级
                if (nowyxj > topOpryxj) {
                    //把当前操作符入栈即可
                    oprStack.push(eachChar);
                } else {
                    //要先取出运算
                    calculate(numStack, oprStack);
                    oprStack.push(eachChar);
                }
            }
        }
        //上面只是对表达式进行了入栈,当全部入栈后,就要再次运算
        lastCalculate(numStack, oprStack);

        //最后处理完了,运算法栈里面的数就是结果
        System.out.println(expression + " 的值是 : " + numStack.pop());

    }


    public static void lastCalculate(Stack numStack, Stack oprStack) {
        while (!oprStack.isEmpty()) {
            //只要操作符栈不是空,就一直运算即可
            calculate(numStack, oprStack);
        }
    }

    private static void calculate(Stack numStack, Stack oprStack) {
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        int opr = oprStack.pop();
        int result = operation(num1, num2, (char) opr);
        numStack.push(result);//运算后的结果入操作数栈
    }

    public static boolean isOpr(char c) {//判断是否是一个操作符
        return c == '*' || c == '/' || c == '+' || c == '-'|| c=='('||c ==')';
    }

    public static int getPriority(char str) {//获取运算符的优先级
        if ('*' == str || '/' == str) {
            return 1;
        } else if ('-' == str || '+' == str) {
            return 0;
        }
        throw new RuntimeException("表达式有误!");
    }

    public static int operation(int num1, int num2, char opr) {
        int result = 0;
        switch (opr) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                // 处理非法操作符的情况
                throw new IllegalArgumentException("操作符非法: " + opr);
        }
        return result;
    }


}

class Stack {

    private int[] array;
    private int maxSize;
    private int stackTop = -1;//注意,这里栈顶元素就是指向栈顶元素的位置,没有偏移


    Stack(int num) {
        maxSize = num;
        array = new int[num];
    }


    public int getTopNum() {
        if (isEmpty()) throw new RuntimeException("栈为空,无栈顶元素!");
        return array[stackTop];
    }

    public boolean isEmpty() {
        return this.stackTop == -1;
    }

    public boolean isFull() {
        return this.stackTop == maxSize - 1;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("栈为空,不可取出!");
        return array[stackTop--];
    }

    public void push(int num) {
        if (isFull()) throw new RuntimeException("栈已经满了,不可以添加!!");
        array[++stackTop] = num;
    }

    public void show() {
        if (isEmpty()) throw new RuntimeException("栈为空,不可遍历!");
        int temp = stackTop;
        while (temp != -1) {
            System.out.print(array[temp--] + "->");
        }
    }
}

