public class DoubleLinkedListUser {
    public static void main(String[] args) {
        DoubleLinkedList dl = new DoubleLinkedList();
        HeroDoubleLinkedListNode head = dl.getHead();

        dl.addByOrder(new HeroDoubleLinkedListNode(2, "卢俊义", "玉麒麟"));
        dl.addByOrder(new HeroDoubleLinkedListNode(1, "宋江", "及时雨"));
        dl.addByOrder(new HeroDoubleLinkedListNode(3, "吴用", "智多星"));
        dl.addByOrder(new HeroDoubleLinkedListNode(4, "公孙胜", "入云龙"));
//        dl.show();
//        dl.update(new HeroDoubleLinkedListNode(2, "小卢", "玉麒麟"));
//        dl.show();
//          dl.del(new HeroDoubleLinkedListNode(2, "卢俊义", "玉麒麟"));
          dl.show();
    }
}

/**
 * 水浒英雄双向链表管理类
 */
class DoubleLinkedList {
    /**
     * 双向链表表头
     */
    private HeroDoubleLinkedListNode head = new HeroDoubleLinkedListNode(0, "", "");

    /**
     * 获取双向链表头
     * @return
     */
    public HeroDoubleLinkedListNode getHead() {
        return head;
    }

    /**
     * 双向链表的添加
     * @param value
     */
    public void add(HeroDoubleLinkedListNode value) {
        // head不能动，因此引入辅助变量temp
        HeroDoubleLinkedListNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = value;
        value.pre = temp;
    }

    /**
     * 顺序添加元素到双向链表
     * @param value
     */
    public void addByOrder(HeroDoubleLinkedListNode value) {
        HeroDoubleLinkedListNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no > value.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = value;
            value.next = temp;
            temp.pre = value;
            value.pre = temp.pre;
        } else {
            this.add(value);
        }
    }

    /**
     * 遍历双向链表
     */
    public void show() {
        if (head.next == null) {
            System.out.println("双向链表为空~~");
            return;
        }
        // 因为头结点不能动，因此使用辅助变量temp
        HeroDoubleLinkedListNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    /**
     * 修改双向链表的节点内容
     * @param value
     */
    public void update(HeroDoubleLinkedListNode value) {
        if (head.next == null) {
            System.out.println("双向链表为空~~");
            return;
        }
        // 因为head不能动，引入temp变量
        HeroDoubleLinkedListNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == value.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = value.name;
            temp.nickName = value.nickName;
        } else {
            System.out.println("未找到该元素~~");
        }
    }

    /**
     * 删除双向链表的某个节点
     * @param value
     */
    public void del(HeroDoubleLinkedListNode value) {
        if (head.next == null) {
            System.out.println("双向链表为空~~");
            return;
        }
        // 使用temp变量遍历链表
        HeroDoubleLinkedListNode temp = head.next;
        boolean falg = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == value.no) {
                falg = true;
                break;
            }
            temp = temp.next;
        }
        if (falg) {
            if (temp.next == null) {
                temp.pre.next = null;
            } else {
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("无法找到该元素~~");
        }
    }
}

/**
 * 水浒英雄双向链表节点
 */
class HeroDoubleLinkedListNode {
    public HeroDoubleLinkedListNode pre;
    public HeroDoubleLinkedListNode next;
    public int no;
    public String name;
    public String nickName;

    @Override
    public String toString() {
        return "HeroDoubleLinkedListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public HeroDoubleLinkedListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
}