public class BinarySortTreeUser {
    public static void main(String[] args) {
        BinarySortTree bst = new BinarySortTree();
        BinarySortTreeNode node = new BinarySortTreeNode(3);
        bst.createBinarySortTree(node);
        BinarySortTreeNode node1 = new BinarySortTreeNode(4);
        bst.createBinarySortTree(node1);
        BinarySortTreeNode node2 = new BinarySortTreeNode(5);
        bst.createBinarySortTree(node2);
        BinarySortTreeNode node3 = new BinarySortTreeNode(1);
        bst.createBinarySortTree(node3);
        BinarySortTreeNode node4 = new BinarySortTreeNode(6);
        bst.createBinarySortTree(node4);
        BinarySortTreeNode node5 = new BinarySortTreeNode(2);
        bst.createBinarySortTree(node5);
        BinarySortTreeNode node7 = new BinarySortTreeNode(2);
        bst.createBinarySortTree(node7);
        BinarySortTreeNode node6 = new BinarySortTreeNode(0);
        bst.createBinarySortTree(node6);
        BinarySortTreeNode node8 = new BinarySortTreeNode(-1);
        bst.createBinarySortTree(node8);
        bst.preOrder();
        bst.delete(4);
        bst.preOrder();
    }
}

/**
 * 二叉排序树
 */
class BinarySortTree {

    public BinarySortTreeNode root;

    /**
     * 创建二叉排序树
     * @param node
     */
    public void createBinarySortTree(BinarySortTreeNode node) {
        if (root == null) {
            root = node;
            return;
        }
        root.createBinarySortTree(node);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空，无法遍历~~");
            return;
        }
        root.preOrder();
    }

    /**
     * 根据节点的值删除节点
     * @param value
     */
    public void delete(int value) {
        if (root == null) {
            System.out.println("二叉树为空，无法删除~");
            return;
        }
        if (value == root.value) {
            root = null;
            System.out.println("删除成功~");
            return;
        }
        root.delete(value);
    }
}

/**
 * 二叉排序树节点
 */
class BinarySortTreeNode {

    int value;
    BinarySortTreeNode left;
    BinarySortTreeNode right;

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 创建二叉排序树
     * @param node
     */
    public void createBinarySortTree(BinarySortTreeNode node) {
        if (node.value < this.value) {
            if (this.left != null) {
                this.left.createBinarySortTree(node);
            } else {
                this.left = node;
            }
        } else {
            if (this.right != null) {
                this.right.createBinarySortTree(node);
            } else {
                this.right = node;
            }
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this.value);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 根据值删除二叉树的节点
     * @param value
     */
    public void delete(int value) {
        if (this.left != null) {
            if (this.left.value == value) {
                // 左子节点为待删除节点
                System.out.println("删除成功~");
                if (this.left.left == null && this.left.right == null) {
                    // 待删除节点是叶子结点
                    this.left = null;
                } else if (this.left.right == null) {
                    // 待删除节点有左子节点
                    this.left = this.left.left;
                } else if (this.left.left == null) {
                    // 待删除节点有右子节点
                    this.left  = this.left.right;
                } else {
                    // 待删除节点有左右子节点
                    // 定义一个临时节点来遍历
                    BinarySortTreeNode temp = this.left.right;
                    // 定义一个临时节点，用来存储代换节点
                    BinarySortTreeNode tempNode = null;
                    if (temp.left == null) {
                        // 如果待删除节点的右子节点是没有左子节点
                        tempNode = temp;
                        // 替换节点
                        tempNode.left = this.left.left;
                    } else {
                        // 如果待删除节点的右子节点不是叶子节点
                        while (true) {
                            if (temp.left.left == null) {
                                break;
                            }
                            temp = temp.left;
                        }
                        // 将代换节点从树上取下
                        tempNode = temp.left;
                        temp.left = null;
                        // 替换节点
                        tempNode.left = this.left.left;
                        tempNode.right = this.left.right;
                    }
                    this.left = tempNode;
                }
            } else {
                this.left.delete(value);
            }
        }
        if (this.right != null) {
            if (this.right.value == value) {
                // 右子节点为待删除节点
                System.out.println("删除成功~");
                if (this.right.left == null && this.right.right == null) {
                    // 待删除节点是叶子结点
                    this.right = null;
                } else if (this.right.right == null) {
                    // 待删除节点有左子节点
                    this.right = this.right.left;
                } else if (this.right.left == null) {
                    // 待删除节点有右子节点
                    this.right  = this.right.right;
                } else {
                    // 待删除节点有左右子节点
                    // 定义一个临时节点来遍历
                    BinarySortTreeNode temp = this.right.right;
                    // 定义一个临时节点，用来存储代换节点
                    BinarySortTreeNode tempNode = null;
                    if (temp.left == null) {
                        // 如果待删除节点的右子节点是没有左子节点
                        tempNode = temp;
                        // 替换节点
                        tempNode.left = this.left.left;
                    } else {
                        // 如果待删除节点的右子节点不是叶子节点
                        while (true) {
                            if (temp.left.left == null) {
                                break;
                            }
                            temp = temp.left;
                        }
                        // 将代换节点从树上取下
                        tempNode = temp.left;
                        temp.left = null;
                        // 替换节点
                        tempNode.left = this.right.left;
                        tempNode.right = this.right.right;
                    }
                    this.right = tempNode;
                }
            } else {
                this.right.delete(value);
            }
        }
    }
}
