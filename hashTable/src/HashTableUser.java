public class HashTableUser {
    public static void main(String[] args) {
        HashTable table = new HashTable(10);
        EmployeeNode emp1 = new EmployeeNode(1, "张三");
        table.add(emp1);
        EmployeeNode emp2 = new EmployeeNode(12, "王五");
        table.add(emp2);
        table.select(1);
        table.delete(1);
        table.select(1);
    }
}

/**
 * 哈希表
 */
class HashTable {

    private Employee[] employeeArray;

    private int size;

    public HashTable(int size) {
        // 初始化员工链表数组
        this.size = size;
        employeeArray = new Employee[size];
        // 初始化数组中的每个链表
        for (int i = 0; i < size; i++) {
            employeeArray[i] = new Employee();
        }
    }

    /**
     * 添加元素进哈希表
     * @param emp
     */
    public void add(EmployeeNode emp) {
        // 获取要添加的员工的id
        int empId = emp.id;
        // 根据映射函数寻找要添加到哈希表中的位置
        int index = hashFund(empId);
        // 添加
        employeeArray[index].add(emp);
    }

    /**
     * 遍历哈希表
     */
    public void show() {
        for (int i = 0; i < size; i++) {
            employeeArray[i].show(i);
        }
    }

    /**
     * 根据id查找
     * @param id
     */
    public void select(int id) {
        int index = hashFund(id);
        System.out.printf("第%d链表：", index);
        employeeArray[index].select(id);
    }

    /**
     * 根据id删除元素
     * @param id
     */
    public void delete(int id) {
        int index = hashFund(id);
        System.out.printf("第%d链表：", index);
        employeeArray[index].delete(id);
    }

    /**
     * 散列函数
     * 通过简单的模运算进行映射
     * @param id
     * @return
     */
    public int hashFund(int id) {

        return id % size;

    }
}

/**
 * 链表
 */
class Employee {

    private EmployeeNode head = null;

    public EmployeeNode getHead() {
        return head;
    }

    /**
     * 添加雇员
     * @param value
     */
    public void add(EmployeeNode value) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = value;
        } else {
            EmployeeNode temp = head;

            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = value;
        }
    }

    /**
     * 遍历链表
     */
    public void show(int no) {
        // 如果head为空
        if (head == null) {
            System.out.printf("第%d链表为空~~\n", no);
            return;
        }
        System.out.printf("第%d链表的信息为：", no);
        EmployeeNode temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.printf("id=%d\n", temp.id);
            temp = temp.next;
        }
    }

    /**
     * 根据id查找元素
     * @param id
     */
    public void select(int id) {
        if (head == null || id < head.id) {
            System.out.printf("第%d元素不存在", id);
            return;
        }
        EmployeeNode temp = head;
        while (true) {
            if (temp == null) {
                System.out.printf("第%d元素不存在", id);
                return;
            }
            if (temp.id == id) {
                break;
            }
            temp = temp.next;
        }
        System.out.printf("第%d元素的姓名为%s", id, temp.name);
    }

    /**
     * 根据id删除节点
     * @param id
     */
    public void delete(int id) {
        if (head == null || id < head.id) {
            System.out.printf("要删除的元素%d不存在", id);
            return;
        }
        if (head.id == id) {
            head = head.next;
            System.out.printf("第%d元素已删除", id);
        } else {
            EmployeeNode temp = head;
            while (true) {
                if (temp.next == null) {
                    System.out.printf("要删除的元素%d不存在", id);
                    return;
                }
                if (temp.next.id == id) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = temp.next.next;
            System.out.printf("第%d元素已删除", id);
        }
    }
}

/**
 * 员工链表节点类
 */
class EmployeeNode {
    int id;
    String name;

    public EmployeeNode(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    EmployeeNode next;
}
