import java.lang.reflect.Array;

public class ArrayStackUser {
    public static void main(String[] args) {
        String e = "9-7*1+1*1+1";
        int res = new SimpleCalculator().calSimple(e);
        System.out.printf("%s=%d", e, res);
    }
}

class SimpleCalculator {
    /**
     * 对一个运算表达式字符串进行计算
     * 简单运算，但是无法进行多位数运算，无法进行括号运算
     *
     * @param e
     * @return
     */
    public int calSimple(String e) {
        // 创建两个栈，一个存储数，一个存储运算符
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        char c = ' ';
        int res = 0;

        // 遍历字符串
        while (true) {
            c = e.charAt(index);
            // 判断是什么字符
            // 判断是否为运算符
            if (operStack.isOper(c)) {
                // 是运算符
                // 判断当前运算符栈是否为空
                if (!operStack.isEmpty()) {
                    // 运算符栈不为空
                    // 判断当前运算符和栈顶运算符的优先级
                    if (operStack.priority(c) <= operStack.peek()) {
                        // 当前运算符优先级低于栈顶运算符
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        numStack.push(res);
                    }
                }
                operStack.push(c);
            } else {
                // 不是运算符，将char类型的数字转为int类型存入
                numStack.push(c - 48);
            }
            index++;
            if (index >= e.length()) {
                break;
            }
        }

        // 运算数栈和符号栈的元素
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        // 将最后的结果从栈中弹出
        return numStack.pop();
    }
}


/**
 * 数字/符号栈
 * 因为char底层是int数组，因此可以和数字使用一个栈
 */
class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1; // 栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    /**
     * 判断栈满
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        // 判断是否栈满
        if (this.isFull()) {
            System.out.println("栈满");
            return;
        }
        this.top = this.top + 1;
        this.stack[this.top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     */
    public void show() {
        if (this.isEmpty()) {
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符优先级，优先级越高值越大
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 3;
        } else {
            if ( oper == '-') {
                return 2;
            } else {
                if (oper == '+') {
                    return 1;
                } else {
                    return -1; // 假定此运算器只有+、-、*、/运算符
                }
            }
        }
    }

    /**
     * 判断该字符是否为运算符
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    /**
     * 计算方法
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public int peek() {
        return stack[top];
    }
}
