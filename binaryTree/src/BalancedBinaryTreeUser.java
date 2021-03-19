public class BalancedBinaryTreeUser {
    public static void main(String[] args) {
        BalancedBinaryTree avl = new BalancedBinaryTree();
        int[] arr1 = {10, 11, 7, 6, 8, 9};
        for (int i :
                arr1) {
            avl.add(new BalancedBinaryTreeNode(i));
        }
        avl.infixOrder();
        System.out.println(avl.getRoot().value);
        System.out.println(avl.getRoot().left.value);
        System.out.println(avl.getRoot().right.value);
        System.out.println(avl.getRoot().left.left.value);
    }
}

/**
 * 平衡二叉树
 */
class BalancedBinaryTree {

    private BalancedBinaryTreeNode root;

    /**
     * 创建二叉排序树
     * @param node
     */
    public void add(BalancedBinaryTreeNode node) {
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
        if (root.getLeftTreeHeight() - root.getRightTreeHeight() > 1) {
            root.rightRotate();
            return; // 非常重要，一定要添加
        }
        if (root.getRightTreeHeight() - root.getLeftTreeHeight() > 1) {
            root.leftRotate();
        }
    }

    /**
     * 中序遍历平衡二叉树
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空~");
            return;
        }
        root.infixOrder();
    }

    /**
     * 获取平衡二叉树根节点
     * @return
     */
    public BalancedBinaryTreeNode getRoot() {
        return this.root;
    }
}

/**
 * 平衡二叉树节点类
 */
class BalancedBinaryTreeNode {
    int value;
    BalancedBinaryTreeNode left;
    BalancedBinaryTreeNode right;

    public BalancedBinaryTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BalancedBinaryTreeNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点进入平衡二叉树
     * @param node
     */
    public void add(BalancedBinaryTreeNode node) {
        if (node.value < this.value) {
            if (this.left != null) {
                this.left.add(node);
            } else {
                this.left = node;
            }
        } else {
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        }
        if (this.getLeftTreeHeight() - this.getRightTreeHeight() > 1) {
            this.rightRotate();
            return; // 非常重要，一定要添加
        }
        if (this.getRightTreeHeight() - this.getLeftTreeHeight() > 1) {
            this.leftRotate();
        }
    }

    /**
     * 返回当前节点的右子树高度
     * @return
     */
    public int getRightTreeHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.getNodeHeight();
    }

    /**
     * 返回当前节点的左子树高度
     * @return
     */
    public int getLeftTreeHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.getNodeHeight();
    }

    /**
     * 返回以当前节点为根节点的树的高度
     * @return
     */
    public int getNodeHeight() {
        return Math.max(this.left == null ? 0 : this.left.getNodeHeight(), this.right == null ? 0 : this.right.getNodeHeight()) + 1;
    }

    /**
     * 左旋转当前节点(考虑双旋转)
     */
    public void leftRotate() {

        BalancedBinaryTreeNode r = this.right;
        if (r.getLeftTreeHeight() > r.getRightTreeHeight()) {
            //考虑双旋转
            // 先让l右旋转
            r.rightRotate();
        }

        BalancedBinaryTreeNode temp = new BalancedBinaryTreeNode(this.value);
        temp.left = this.left;
        temp.right = this.right.left;
        this.left = temp;

        this.value = this.right.value;
        this.right = this.right.right;
    }

    /**
     * 右旋转当前节点(考虑双旋转)
     */
    public void rightRotate() {

        BalancedBinaryTreeNode l = this.left;
        if (l.getRightTreeHeight() > l.getLeftTreeHeight()) {
            // 考虑双旋转
            l.leftRotate();
        }

        BalancedBinaryTreeNode temp = new BalancedBinaryTreeNode(this.value);
        temp.right = this.right;
        temp.left = this.left.right;
        this.right = temp;

        this.value = this.left.value;
        this.left = this.left.left;
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
