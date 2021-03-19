package com.yuu.day01;

/**
 * 已知一个搜索二叉树后序遍历后的数组posArr，请根据posArr，重建出整棵树，返回新建树的头结点
 */
public class Section1 {

    public static void main(String[] args) {
        int[] posArr = {2, 4, 3, 6, 8, 7, 5};
        TreeNode treeNode = processToBST1(posArr);
        System.out.println(treeNode.node);
        System.out.println(treeNode.left.node);
        System.out.println(treeNode.right.node);
    }

    /**
     * 第一个解决方法。没有优化
     * @return
     */
    public static TreeNode processToBST1(int[] posArr) {
        return process1(0, posArr.length - 1, posArr);
    }

    /**
     * 递归还原二叉排序树，最终返回二叉排序树的根节点
     * @param n 待重构的数组左指针
     * @param m 待重构的数组右指针
     * @param posArr
     * @return
     */
    public static TreeNode process1(int n, int m, int[] posArr) {

        /* 递归结束条件*/
        if (n > m) {
            return null;
        }

        /* 数组的n-1号索引一定是重构的根节点，重构根节点 */
        TreeNode head = new TreeNode();
        head.node = posArr[m];

        // 如果左右指针相等，直接返回head。同时还可以解决数组长度为一的问题
        if (n == m) {
            return head;
        }


        /* 循环重构左树 */
        // 找到左树的范围

        // 最为重要的一点，这个是为了防止没有左子树的情况，即左边没有小于posArr[m]的值。
        // 一旦没有左子树，则temp=n-1进入section1(n, temp, posArr);，直接返回null
        int temp = n - 1;

        for (int i = n; i < m; i++) {
            if (posArr[i] < posArr[m]) {
                temp = i;
            }
        }
        // 构建左树
        // 拼接
        head.left = process1(n, temp, posArr);

        /* 递归重构右树 */
        // 根据找到的左树范围，推出右树范围。构建右树
        head.right = process1(temp + 1, m - 1, posArr);

        return head;
    }

    /**
     * 第二种方案。使用二分查找优化
     * @param n
     * @param m
     * @param posArr
     * @return
     */
    public static TreeNode process2(int n, int m, int[] posArr) {
        /* 递归结束条件*/
        if (n > m) {
            return null;
        }

        /* 数组的n-1号索引一定是重构的根节点，重构根节点 */
        TreeNode head = new TreeNode();
        head.node = posArr[m];

        // 如果左右指针相等，直接返回head。同时还可以解决数组长度为一的问题
        if (n == m) {
            return head;
        }


        /* 循环重构左树 */
        // 找到左树的范围

        // 最为重要的一点，这个是为了防止没有左子树的情况，即左边没有小于posArr[m]的值。
        // 一旦没有左子树，则temp=n-1进入section1(n, temp, posArr);，直接返回null
        int temp = n - 1;

        // 使用二分查找找到左子树范围
        int l = n;
        int r = m - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1); // 等效于mid = n + (m - n) / 2。但是移位比除法底层要快。因此用移位。
            if (posArr[mid] < posArr[m]) {
                temp = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        // 构建左树
        // 拼接
        head.left = process2(n, temp, posArr);

        /* 递归重构右树 */
        // 根据找到的左树范围，推出右树范围。构建右树
        head.right = process2(temp + 1, m - 1, posArr);

        return head;
    }

}

/**
 * 二叉排序树的节点
 */
class TreeNode {
    int node;
    TreeNode left;
    TreeNode right;
}
