import java.util.ArrayList;
import java.util.Scanner;

public class ArrayQueueUser {
    public static void main(String[] args) {
        CircleQueue aq = new CircleQueue(4);
        // 接收用户输入
        char key = ' ';
        // 接收用户输入的队列的值
        int value = 0;
        Scanner scanner = new Scanner(System.in);
        //输出一个菜单
        boolean loop = true;
        while (loop) {
            System.out.println("s: 显示队列");
            System.out.println("e: 退出程序");
            System.out.println("a: 添加数据");
            System.out.println("g: 获取数据");
            System.out.println("h: 头数据");
            //获取用户输入的字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    aq.showQueue();
                    break;
                case 'e':
                    loop = false;
                    break;
                case 'a':
                    value = scanner.nextInt();
                    aq.addQueue(value);
                    break;
                case 'g':
                    System.out.println(aq.getQueue());
                    break;
                case 'h':
                    System.out.println(aq.headQueue());
                    break;
                default:
                    loop = false;

            }
        }
    }
}

class ArrQueue {

    private int maxSize;
    private int font;
    private int rear;
    private int[] queue;

    public ArrQueue(int size) {
        //初始化队列
        maxSize = size;
        font = -1;
        rear = -1;
        queue = new int[maxSize];
    }

    /**
     * 队列非空判断
     * @return
     */
    public boolean isEmpty() {
        return font == rear;
    }

    /**
     * 队列为满判断
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 给队列添加值
     * @param value
     */
    public void addQueue(int value) {
        //首先判断队列是否为满
        if (isFull()) {
            System.out.println("队列已满，无法添加~~");
            return;
        }
        // 先让队尾指针后移
        rear++;
        // 添加元素进入队列
        queue[rear] = value;
    }

    /**
     * 队列取值
     * @return
     */
    public int getQueue() {
        // 首先判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取值");
        }
        // 先让队首指针后移一位
        font++;
        // 获取值
        return queue[font];
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        // 首先判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空，不能显示值~~");
            return;
        }
        // 从队列头一个元素开始遍历，遍历到队列最后一个元素
        for (int i = font + 1; i <= rear; i++) {
            System.out.printf("queue[%d]=%d\n", i, queue[i]);
        }
    }

    /**
     * 显示队列的头部数据
     * @return
     */
    public int headQueue() {
        // 判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法展示~~");
        }
        //展示数据
        return queue[font + 1];
    }
}

class CircleQueue {

    private int maxSize;
    private int font;
    private int rear;
    private int[] queue;

    public CircleQueue(int size) {
        //初始化队列
        maxSize = size;
        font = 0;
        rear = 0;
        queue = new int[maxSize];
    }

    /**
     * 队列非空判断
     * @return
     */
    public boolean isEmpty() {
        return font == rear;
    }

    /**
     * 队列为满判断
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == font;
    }

    /**
     * 给队列添加值
     * @param value
     */
    public void addQueue(int value) {
        //首先判断队列是否为满
        if (isFull()) {
            System.out.println("队列已满，无法添加~~");
            return;
        }
        //因为rear从第一个元素开始，初始值为0，因此先给队列赋值
        queue[rear] = value;
        //然后增加，注意，因为是环形数组，因此不能普通的递增
        rear = (rear + 1) % maxSize;
    }

    /**
     * 队列取值
     * @return
     */
    public int getQueue() {
        // 首先判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取值");
        }
        //因为rear从第一个元素开始，初始值为0，先将队列的值赋给一个临时变量
        int temp = queue[font];
        //递增,注意，因为是环形数组，因此不能普通的递增
        font = (font + 1) % maxSize;
        //返回队列的值
        return temp;
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        // 首先判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空，不能显示值~~");
            return;
        }
        // 从队列头一个元素开始遍历，遍历到队列最后一个元素
        for (int i = font ; i < font + size(); i++) {
            // 要小心i可能超出maxSize的情况，因此在环形xxx中，数据最好都使用i % maxSize来处理
            System.out.printf("queue[%d]=%d\n", i % maxSize, queue[i % maxSize]);
        }
    }

    /**
     * 显示队列有效元素个数
     * @return
     */
    public int size() {
        return (rear + maxSize - font) % maxSize;
    }

    /**
     * 显示队列的头部数据
     * @return
     */
    public int headQueue() {
        // 判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法展示~~");
        }
        //展示数据
        return queue[font];
    }
}
