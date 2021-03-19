public class ArrayStoreBinaryTree {
    private static int[] arr = {1, 2, 3, 4, 5, 6, 7};
    public static void main(String[] args) {
        postShow(0);
    }

    /**
     * 前序遍历
     * 对存储了二叉树的顺序表使用前序遍历
     * @param index
     */
    public static void preShow(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法遍历~");
            return;
        }
        // 输出当前元素
        System.out.println(arr[index]);
        // 向左递归
        if ((index * 2 + 1) < arr.length) {
            preShow(2 * index + 1);
        }
        // 向右递归
        if ((index * 2 + 2) < arr.length) {
            preShow(2 * index + 2);
        }
    }

    /**
     * 中序遍历
     * 对存储了二叉树的顺序表使用前序遍历
     * @param index
     */
    public static void infixShow(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法遍历~");
            return;
        }
        // 向左递归
        if ((index * 2 + 1) < arr.length) {
            infixShow(2 * index + 1);
        }
        // 输出当前元素
        System.out.println(arr[index]);
        // 向右递归
        if ((index * 2 + 2) < arr.length) {
            infixShow(2 * index + 2);
        }
    }

    /**
     * 后序遍历
     * 对存储了二叉树的顺序表使用前序遍历
     * @param index
     */
    public static void postShow(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法遍历~");
            return;
        }
        // 向左递归
        if ((index * 2 + 1) < arr.length) {
            postShow(2 * index + 1);
        }
        // 向右递归
        if ((index * 2 + 2) < arr.length) {
            postShow(2 * index + 2);
        }
        // 输出当前元素
        System.out.println(arr[index]);
    }
}
