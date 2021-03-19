import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Caculator {
    public static void main(String[] args) {
        NiBoLan ni = new NiBoLan();
        System.out.println(ni.cal(ni.change("(2+3)*4+3*1")));
    }
}

class NiBoLan {
    /**
     * 中缀表达式转后缀表达式
     * @param e
     * @return
     */
    public String change(String e) {
        // 将中缀表达式中的运算符和运算数取出组成一个String集合以方便计算
        List<String> zhongjian = new ArrayList<>();
        char c = ' ';
        int index = 0;
        String temp = "";
        do {
            if ((c = e.charAt(index)) < 48 || (c = e.charAt(index)) > 57) {
                zhongjian.add(c + "");
                index++;
            } else {
                temp = "";
                while (index < e.length() && (c = e.charAt(index)) >= 48 && (c = e.charAt(index)) <= 57) {
                    temp = temp + c;
                    index++;
                }
                zhongjian.add(temp);
            }
        } while (index < e.length());

        // 转换
        Stack<String> stack = new Stack<>();
        int indexx = 0;
        String res = "";
        List<String> nibolan = new ArrayList<>();
        // 因为使用栈来存储转换后的后缀表达式的各个元素，需要先出栈后再反转，因此为了方便
        // 使用集合来存储，就可以不用反转
        for (String s :
                zhongjian) {
            if (s.charAt(0) >= 48 && s.charAt(0) <= 57) {
                nibolan.add(s);
            } else if (s.charAt(0) == '(') {
                stack.push(s);
            } else if (s.charAt(0) == ')') {
                while (!stack.peek().equals("(")) {
                    nibolan.add(stack.pop());
                }
                stack.pop();
            } else if (stack.size() == 0 || stack.peek().equals("(")) {
                stack.push(s);
            } else if (youXianJi(s) > youXianJi(stack.peek())) {
                stack.push(s);
            } else {
                nibolan.add(stack.pop());
                while (true) {
                    if (stack.size() == 0 || stack.peek().equals("(") || youXianJi(s) > youXianJi(stack.peek())) {
                        stack.push(s);
                        break;
                    } else {
                        nibolan.add(stack.pop());
                    }
                }
            }
        }
        while (stack.size() != 0) {
            nibolan.add(stack.pop());
        }
        for (String s :
                nibolan) {
            res = res + s;
        }
        return res;
    }

    /**
     * 计算一个运算符的优先级
     * @param e
     * @return
     */
    public int youXianJi(String e) {
        if (e.charAt(0) == '+' || e.charAt(0) == '-') {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 扫描逆波兰表达式并计算出结果
     * @param e
     * @return
     */
    public int cal(String e) {

        Stack<Integer> stack = new Stack<>();
        int index = 0; // 索引
        char c = ' '; // 存放字符
        int num1 = 0;
        int num2 = 0;
        while(true) {
            if (index >= e.length()) {
                break;
            }
            c = e.charAt(index);
            // 如果是运算数
            if (c >= 48 && c <= 57) {
                stack.push(Integer.parseInt(c + ""));
            } else {
                // 如果是运算符
                num1 = stack.pop();
                num2 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(num2 + num1);
                        break;
                    case '-':
                        stack.push(num2 - num1);
                        break;
                    case '*':
                        stack.push(num2 * num1);
                        break;
                    case '/':
                        stack.push(num2 / num1);
                        break;
                }
            }
            index++;
        }
        return stack.pop();
    }
}
