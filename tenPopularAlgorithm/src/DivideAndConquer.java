/**
 * 分治算法
 * 以汉诺塔问题为例子
 */
public class DivideAndConquer {
    private static int count = 0;
    public static void main(String[] args) {
        int num = 16; // 初始化汉诺塔的层数
        hanoi(num, "A", "B", "C");
        System.out.println(count);
    }

    /**
     * 将a上的n层圆盘通过b移动到c
     * @param n 汉诺塔层数
     * @param a n层所在的塔
     * @param b 过渡用的塔
     * @param c 第n层应该去的塔
     */
    public static void hanoi(int n, String a, String b, String c) {
        if (n == 1) {
            // 如果a塔上只有一层，直接把圆盘从a移动到c，算法结束
            System.out.println(a + "移动到" + c);
            count++;
        } else {
            // 如果a塔上有多于一层的圆盘
            // 先让上面的n-1层通过c，从a移动到b
            hanoi(n - 1, a, c, b);
            // 让第n层的圆盘直接从a移动到c
            System.out.println(a + "移动到" + c);
            count++;
            // 让剩下的圆盘通过a，从b移动到c
            hanoi(n-1, b, a, c);
        }
    }
}
