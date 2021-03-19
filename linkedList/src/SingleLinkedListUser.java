import java.util.Stack;

public class SingleLinkedListUser {
    public static void main(String[] args) {
        // 管理和创建一个新单链表
        SingleLinkedList hero = new SingleLinkedList();
        // 给链表添加数据
        hero.addByOrder(new HeroLinkedListNode(1, "宋江", "及时雨"));
        hero.addByOrder(new HeroLinkedListNode(2, "卢俊义", "玉麒麟"));

        hero.addByOrder(new HeroLinkedListNode(4, "公孙胜", "入云龙"));

        hero.addByOrder(new HeroLinkedListNode(6, "林冲", "豹子头"));
        hero.addByOrder(new HeroLinkedListNode(5, "关胜", "大刀"));
        hero.addByOrder(new HeroLinkedListNode(3, "吴用", "智多星"));
//        // 展示数据
//        hero.showList(hero.getHead());

        System.out.println();

//        // 给链表修改一个节点数据
//        hero.update(new HeroLinkedListNode(6, "冲", "豹子头"));
//        // 展示数据
//        hero.showList();

//        // 删除数据
//        hero.delete(1);
//        hero.delete(2);
//        hero.delete(3);
//        hero.delete(4);
//        hero.delete(5);
//        hero.delete(6);
//        hero.delete(1);
//
//        hero.showList();

//        // 打印链表节点个数
//        int length = hero.getLength(hero.getHead());
//        System.out.println(length);

//        //获取倒数第二个节点
//        HeroLinkedListNode lastIndex = hero.findLastIndexNode(6, hero.getHead());
//        if (lastIndex == null) {
//            System.out.println("找不到对应节点");
//        } else {
//            System.out.println(lastIndex.toString());
//        }
//        // 反转链表方法2：
//        HeroLinkedListNode heroLinkedListNode = hero.reverseSingledLinkedListTypeTwo(hero.getHead());
//        hero.showList(heroLinkedListNode);
        // 逆序打印
        hero.reversePrintTypeTwo(hero.getHead());
    }
}

/**
 * 管理和创建单链表
 */
class SingleLinkedList {
    /**
     * 初始化一个head节点，该节点无任何数据
     */
    private HeroLinkedListNode head = new HeroLinkedListNode(0, "", "");

    /**
     * 添加一个节点到单链表中。不考虑no的顺序
     * @param hero
     */
    public void addNoOrder(HeroLinkedListNode hero) {
        // 因为head节点无法动，定义一个辅助变量用来遍历链表
        HeroLinkedListNode temp = head;
        // 遍历链表至找到最后一个节点
        while (true) {
            // 找到最后一个链表节点
            if (temp.next == null) {
                break;
            }
            // 没到最后继续后移
            temp = temp.next;
        }
        //在链表最后添加新节点
        temp.next = hero;
    }

    /**
     * 添加元素进链表。保证数据按照no的从大到小排列
     * @param hero
     */
    public void addByOrder(HeroLinkedListNode hero) {
        // 因为head节点不能动，定义一个temp变量来遍历链表
        HeroLinkedListNode temp = head;
        // 定义一个标识，标志我们链表中是否已经有这个编号了
        boolean flag = false;
        // 遍历链表至找到最后一个节点
        while (true) {
            // 说明已经temp就是最后一个节点，退出
            if (temp.next == null) {
                break;
            }
            // 找到了插入位置的前一个节点，退出
            if (temp.next.no > hero.no) {
                break;
            }
            // 如果已经存在，让flag置为true，退出
            if (temp.next.no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("编号%d已经存在~~", hero.no);
        } else {
            hero.next = temp.next;
            temp.next = hero;
        }

    }

    /**
     * 遍历链表并展示每个节点
     */
    public void showList(HeroLinkedListNode head) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        // 因为head节点不能动，定义一个辅助变量来遍历链表
            /* 注意：在这里我们要让temp指向链表第一个节点，因为
            *  在遍历时我们必须要先判断temp == null,再输出数据然后再后移，
            *  如果指向head，会造成如果已经到链表末尾则后移后的temp == null，
            *  再输出数据会出错。因此我们必须要让temp指向链表第一个节点而不是head
             */
        HeroLinkedListNode temp = head.next;
        // 遍历链表并展示所有节点的数据
        while (true) {
            // 判断当前是否遍历完成
            if (temp == null) {
                break;
            }
            // 展示节点的数据
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    /**
     * 根据编号修改节点
     * @param heroUpdate
     */
    public void update(HeroLinkedListNode heroUpdate) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！！");
            return;
        }
        // 因为head节点不能动，因此使用一个临时变量temp来遍历链表
        HeroLinkedListNode temp = head.next;
        // 定义一个标志，标志是否找到了节点
        boolean flag = false;
        // 遍历链表
        while (true) {
            // 判断链表是否遍历完成
            if (temp == null) {
                break;
            }
            // 判断是否找到了节点
            if (temp.no == heroUpdate.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 修改链表节点数据
        if (flag) {
            temp.name = heroUpdate.name;
            temp.nickName = heroUpdate.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点", heroUpdate.no);
        }
    }

    /**
     * 根据编号删除节点
     * @param no
     */
    public void delete(int no) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        // 由于head节点无法移动，因此定义temp变量来遍历链表
        HeroLinkedListNode temp = head;
        // 标记是否有对应编号的节点
        boolean flag = false;
        // 遍历获取
        while (true) {
            // 判定是否遍历完毕
            if (temp.next == null) {
                break;
            }
            // 判定是否为编号对应节点的前一个节点
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("无编号%d的节点", no);
        }

    }

    /**
     * 获取链表的节点个数
     * @return
     */
    public int getLength(HeroLinkedListNode head) {
        // 判断是否为空链表
        if (head.next == null) {
            return 0;
        }
        // 定义一个变量count来存储链表节点个数
        int count = 0;
        // 定义一个临时变量来遍历链表
        HeroLinkedListNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            count++;
            temp = temp.next;
        }
        return count;
    }

    public HeroLinkedListNode getHead() {
        return head;
    }

    /**
     * 获取单链表中倒数第k个节点
     * @param lastIndex
     * @param head
     * @return
     */
    public HeroLinkedListNode findLastIndexNode(int lastIndex, HeroLinkedListNode head) {
        // 先判断链表是否为空
        if (head.next == null) {
            return null;
        }
        // 获取链表总长度
        int length = new SingleLinkedList().getLength(head);
        // 判断索引是否合法
        if (lastIndex <= 0 || lastIndex > length) {
            return null;
        }
        // 遍历链表至查到
        HeroLinkedListNode temp = head;
        int count = 0;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (count == length - lastIndex + 1) {
                flag = true;
                break;
            }
            count++;
            temp = temp.next;
        }
        if (flag) {
            return temp;
        } else {
            return null;
        }
    }

    /**
     * 反转单链表
     * 方法1：通过递归实现
     * @param head
     * @return
     */
    public HeroLinkedListNode reverseSingleLinkedListTypeOne(HeroLinkedListNode head) {
        if (head.next == null) {
            return null;
        }
        HeroLinkedListNode reverseHeroHead = new HeroLinkedListNode(0, "", "");
        HeroLinkedListNode reverse = reverseTypeOne(head, reverseHeroHead);
        return reverse;
    }

    private HeroLinkedListNode reverseTypeOne(HeroLinkedListNode head, HeroLinkedListNode heroReverseHead) {
        if (head.next == null) {
            return heroReverseHead;
        }
        head = head.next;
        HeroLinkedListNode temp1 = head;
        HeroLinkedListNode temp2 = reverseTypeOne(head, heroReverseHead);
        HeroLinkedListNode temp3 = temp2;
        while (true) {
            if (temp3.next == null) {
                temp1.next = null;
                temp3.next = temp1;
                break;
            }
            temp3 = temp3.next;
        }
        return temp2;
    }

    /**
     * 反转单链表
     * 方法2：通过遍历和获取
     * @param hero
     * @return
     */
    public HeroLinkedListNode reverseSingledLinkedListTypeTwo(HeroLinkedListNode hero) {
        // 判断单链表是否为空
        if (hero.next == null || hero.next.next == null) {
            return head;
        }
        // 创建一个新链表
        HeroLinkedListNode heroNew = new HeroLinkedListNode(0, "", "");
        // 遍历旧链表
        HeroLinkedListNode temp = head.next;
        HeroLinkedListNode temp1 = null;
        while (temp != null) {
            temp1 = temp.next;
            temp.next = heroNew.next;
            heroNew.next = temp;
            temp = temp1;
        }
        return heroNew;
    }

    /**
     * 逆序打印单链表
     * 方法1：递归打印
     * @param head
     */
    public void reversePrintTypeOne(HeroLinkedListNode head) {
        if (head != null) {
            reversePrintTypeOne(head.next);
            System.out.println(head.next);
        }
    }

    /**
     * 逆序打印
     * 方法2：栈
     * @param head
     */
    public void reversePrintTypeTwo(HeroLinkedListNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        Stack<HeroLinkedListNode> stack = new Stack<>();
        // 遍历链表，压栈
        HeroLinkedListNode temp = head.next;
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }
        // 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}

/**
 * 记录水浒传英雄的链表节点
 */
class HeroLinkedListNode {
    public int no;
    public String name;
    public String nickName;
    public HeroLinkedListNode next;

    /**
     * 构造器
     * @param no
     * @param name
     * @param nickName
     */
    public HeroLinkedListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroLinkedListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}


