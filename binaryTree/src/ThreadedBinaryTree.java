public class ThreadedBinaryTree {
    public static void main(String[] args) {
        HeroThreadedBinaryTree tree = new HeroThreadedBinaryTree();
        HeroThreadedBinaryTreeNode node1 = new HeroThreadedBinaryTreeNode(1, "宋江");
        HeroThreadedBinaryTreeNode node2 = new HeroThreadedBinaryTreeNode(2, "卢俊义");
        HeroThreadedBinaryTreeNode node3 = new HeroThreadedBinaryTreeNode(3, "吴用");
        HeroThreadedBinaryTreeNode node4 = new HeroThreadedBinaryTreeNode(4, "公孙胜");
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        tree.setRoot(node1);

        tree.preThreadedNode(tree.root);
        tree.showPre(tree.root);
    }
}

/**
 * 水浒英雄线索化二叉树
 */
class HeroThreadedBinaryTree {

    /**
     * 定义根节点
     */
    public HeroThreadedBinaryTreeNode root = null;

    /**
     * 定义一个用来存放前驱节点的属性
     */
    public HeroThreadedBinaryTreeNode preNode = null;

    /**
     * 添加根节点
     * @param root
     */
    public void setRoot(HeroThreadedBinaryTreeNode root) {
        this.root = root;
    }

    /**
     * 中序线索化方法
     * @param node
     */
    public void infixThreadedNode(HeroThreadedBinaryTreeNode node) {
        if (node == null) {
            return;
        }
        // 进行线索化
        HeroThreadedBinaryTreeNode left = node.left;
        HeroThreadedBinaryTreeNode right = node.right;
        // 先线索化左子树
        infixThreadedNode(left);

        // 线索化当前节点
        // 1、先处理前驱
        if (node.left == null) {
            node.left = preNode;
            node.leftType = 1;
        }
        // 2、处理后继
        // 当前节点的后继节点在本次关于当前节点的迭代中无法得知，因此我们需要等到后继节点的轮次
        // 那时当前节点就是后继节点的前置，对前置使用后继逻辑
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = 1;
        }
        // 每个节点的前置和后继处理完成后，当前节点成为下一个节点的前置
        preNode = node;
        // 线索化右子树
        infixThreadedNode(right);
    }

    /**
     * 前序线索化方法
     * @param node
     */
    public void preThreadedNode(HeroThreadedBinaryTreeNode node) {
        if (node == null) {
            return;
        }
        // 进行线索化

        // 线索化当前节点
        HeroThreadedBinaryTreeNode left = node.left;
        HeroThreadedBinaryTreeNode right = node.right;
        // 1、先处理前驱
        if (node.left == null) {
            node.left = preNode;
            node.leftType = 1;
        }
        // 2、处理后继
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = 1;
        }
        // 每个节点的前置和后继处理完成后，当前节点成为下一个节点的前置
        preNode = node;

        // 线索化左子树
        preThreadedNode(left);

        // 线索化右子树
        preThreadedNode(right);
    }

    /**
     * 后序线索化节点
     * @param node
     */
    public void postThreadedNode(HeroThreadedBinaryTreeNode node) {
        if (node == null) {
            return;
        }
        // 线索化
        HeroThreadedBinaryTreeNode left = node.left;
        HeroThreadedBinaryTreeNode right = node.right;
        // 先线索化左树
        postThreadedNode(left);
        // 线索化右树
        postThreadedNode(right);
        // 线索化当前节点
        if (node.left == null) {
            node.left = preNode;
            node.leftType = 1;
        }
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = 1;
        }
        preNode = node;
    }

    /**
     * 中序线索化二叉树遍历
     *
     */
    public void show(HeroThreadedBinaryTreeNode node) {
        // 定义一个临时变量来做遍历
        HeroThreadedBinaryTreeNode temp = node;
        while (temp != null) {
            // 找到线索化的节点
            if (temp.leftType == 0) {
                temp = temp.left;
            }
            // 打印该线索化的节点
            System.out.printf("%d英雄是%s\n", temp.no, temp.name);
            // 打印所有的后继节点
            while (temp.rightType == 1) {
                temp = temp.right;
                System.out.printf("%d英雄是%s\n", temp.no, temp.name);
            }
            // 替换遍历的节点
            temp = temp.right;
        }
    }

    /**
     * 前序线索化二叉树遍历
     *
     */
    public void showPre(HeroThreadedBinaryTreeNode node) {
        // 定义一个临时变量来做遍历
        HeroThreadedBinaryTreeNode temp = node;
        while (temp.right != null) {
            // 找到线索化的节点
            if (temp.leftType == 0) {
                System.out.printf("%d英雄是%s\n", temp.no, temp.name);
                temp = temp.left;
            }
            // 打印该线索化的节点
            System.out.printf("%d英雄是%s\n", temp.no, temp.name);
            // 打印所有的后继节点
            while (temp.rightType == 1) {
                temp = temp.right;
                System.out.printf("%d英雄是%s\n", temp.no, temp.name);
            }
            temp = temp.right;
        }
    }
}

/**
 * 水浒英雄线索化二叉树节点
 */
class HeroThreadedBinaryTreeNode {
    public int no;
    public String name;
    public HeroThreadedBinaryTreeNode left;
    public HeroThreadedBinaryTreeNode right;

    /**
     * leftType和rightType区分left和right指向
     * 0代表指向子树，默认为0
     * 1代表指向前驱
     * 2代表指向后继
     */
    public int leftType;
    public int rightType;


    public HeroThreadedBinaryTreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroThreadedBinaryTreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}
