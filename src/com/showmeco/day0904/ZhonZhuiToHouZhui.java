package com.showmeco.day0904;

/**
 * PackageName: com.showmeco.day0904
 * ClassName: ZhonZhuiToHouZhui
 * Description:
 *
 * @Author: @showmeco
 * @Date: 2023/9/4 17:14
 * @Version: 1.0
 */
public class ZhonZhuiToHouZhui {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        Stack tempStack = new Stack(100);
        Stack resultStack = new Stack(100);


        StackCalculateDemo cal = new StackCalculateDemo();//用于计算
        //中缀转后缀表达式
        //思路:   遍历中缀,是数字直接入结果栈,是操作符,要是临时栈是空的,则入栈,要是不为空,若优先级大于栈顶元素,则入栈,否则,先跳出栈顶元素到结果栈
        //再入栈,要是遇到左括号,则直接入栈,其次后面的操作符也入栈,直到遇到的是有括号,则出栈到结果栈......

        for (int i = 0; i < expression.length(); i++) {
            char eachChar = expression.charAt(i);
            int eachCharNum = eachChar - 48;
            if (!StackCalculateDemo.isOpr(eachChar)) {
                //说明不是表达式,直接入结果栈中
                resultStack.push(eachCharNum);
            } else {//为运算符
                if (tempStack.isEmpty()//栈为空
                        || eachChar == '('//eachchar是左括号
                        || tempStack.getTopNum() == '('//栈顶元素是左括号
                        || eachChar != ')' && StackCalculateDemo.getPriority((char) tempStack.getTopNum()) < StackCalculateDemo.getPriority(eachChar)//或者优先级满足
                ) {//直接入临时栈
                    tempStack.push(eachChar);
                    continue;
                } else if (eachChar == ')') {//说明应该弹出栈顶元素到结果栈中,直到遇到(
                    while ((char) tempStack.getTopNum() != '(') {
                        resultStack.push(tempStack.pop());
                    }
                    //再次pop一次,把(弹出
                    tempStack.pop();
                } else {//说明优先级比tempstack栈顶的优先级小于等于,则弹出栈顶的运算符,再把此运算符入栈
                    resultStack.push(tempStack.pop());
                    tempStack.push(eachChar);
                }
            }
        }//到此,全部已经入栈结束,最后把tempstack的元素全部转入resultstack中
        while (!tempStack.isEmpty()) {
            resultStack.push(tempStack.pop());
        }


        //最后取出result中结果,
        //递归   我发现前面写入栈中的数据全是int,取出来无法区分......
        resultStack.show();

    }
}


