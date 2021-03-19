import java.util.HashMap;

public class CircleLinkedLIstUser {
    public static void main(String[] args) {
        CircleLinkedList cl = new CircleLinkedList();
        cl.add(new JosephuCircleLinkedListNode(1, "一"));
        cl.add(new JosephuCircleLinkedListNode(2, "二"));
        cl.add(new JosephuCircleLinkedListNode(3, "三"));
        cl.add(new JosephuCircleLinkedListNode(4, "四"));
        cl.add(new JosephuCircleLinkedListNode(5, "五"));
//        cl.show();
        cl.josephuTow(1, 2, 5);
    }
}

/**
 * 单向环形链表管理类
 */
class CircleLinkedList {

    /**
     * 创建环形链表头结点
     */
    private JosephuCircleLinkedListNode head = new JosephuCircleLinkedListNode(0, "");

    /**
     * 获取环形链表头结点
     * @return
     */
    public JosephuCircleLinkedListNode getHead() {
        return head;
    }

    /**
     * 添加元素到环形链表中
     * @param value
     */
    public void add(JosephuCircleLinkedListNode value) {
        if (value.no < 1) {
            System.out.println("元素错误，无法添加~~");
            return;
        }
        // 定义temp变量完成遍历
        JosephuCircleLinkedListNode temp = head.next;
        while (true) {
            if (head.next == null) {
                temp = head;
                break;
            }
            if (temp.next == head.next) {
                break;
            }
            temp = temp.next;
        }
        temp.next = value;
        value.next = head.next;
    }

    /**
     * 约瑟夫问题
     * @param k
     * @param startNum
     * @param num
     */
    public void josephu(int k, int startNum, int num) {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        if (k < 1 || startNum < 1 || startNum > num ) {
            System.out.println("参数有误");
        }

        JosephuCircleLinkedListNode helper = head.next;
        while (true) {
            if (helper.next == head.next) {
                break;
            }
            helper = helper.next;
        }

        JosephuCircleLinkedListNode first = head.next;
        for (int i = 0; i < k - 1; i++) {
            helper = helper.next;
            first = first.next;
        }

        while (true) {
            // 环形链表只有一个元素
            if (helper == first) {
                break;
            }
            // 移动，出圈
            for (int j = 0; j < startNum - 1; j++) {
                helper = helper.next;
                first = first.next;
            }
            System.out.printf("%d出圈\n", first.no);
            first = first.next;
            helper.next = first;
        }

        System.out.printf("最后%d出圈\n", helper.no);
    }

    /**
     * 约瑟夫问题解决方法2
     * @param k
     * @param startNum
     * @param num
     */
    public void josephuTow(int k, int startNum, int num) {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }

        if (k < 1 || startNum < 1 || startNum > num ) {
            System.out.println("参数有误");
        }

        JosephuCircleLinkedListNode temp = head.next;
        for (int i = 0; i < k - 2 + num; i++) {
            temp = temp.next;
        }

        while (true) {
            // 环形链表只有一个元素
            if (temp.next == temp) {
                break;
            }
            // 移动，出圈
            for (int j = 0; j < startNum - 1; j++) {
                temp = temp.next;
            }
            System.out.printf("%d出圈\n", temp.next.no);
            temp.next = temp.next.next;
        }

        System.out.printf("最后%d出圈\n", temp.no);
    }
    /**
     * 环形链表的遍历
     */
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        // 定义temp变量完成遍历
        JosephuCircleLinkedListNode temp = head.next;
        while (true) {
            System.out.println(temp.toString());
            temp = temp.next;
            if (temp == head.next) {
                break;
            }
        }
    }

}

/**
 * 约瑟夫环单向环形链表节点
 */
class JosephuCircleLinkedListNode {
    public int no;
    public String name;
    public JosephuCircleLinkedListNode next;

    public JosephuCircleLinkedListNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "JosephuCircleLinkedListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
