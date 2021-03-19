public class BinaryTreeUser {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        HeroBinaryTreeNode node1 = new HeroBinaryTreeNode(1, "宋江");
        HeroBinaryTreeNode node2 = new HeroBinaryTreeNode(2, "卢俊义");
        HeroBinaryTreeNode node3 = new HeroBinaryTreeNode(3, "吴用");
        HeroBinaryTreeNode node4 = new HeroBinaryTreeNode(4, "公孙胜");
        HeroBinaryTreeNode node5 = new HeroBinaryTreeNode(5, "关胜");
        HeroBinaryTreeNode node6 = new HeroBinaryTreeNode(6, "林冲");
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node5.left = node6;
        tree.setRoot(node1);
//        HeroBinaryTreeNode heroBinaryTreeNode = tree.preSearch(3);
//        if (heroBinaryTreeNode != null) {
//            System.out.printf("%d英雄是%s", heroBinaryTreeNode.no, heroBinaryTreeNode.name);
//        } else {
//            System.out.println("没找到该英雄");
//        }
        tree.delNodeSimple(5);
    }
}

class BinaryTree {

    private HeroBinaryTreeNode root;

    /**
     * 添加根节点
     * @param root
     */
    public void setRoot(HeroBinaryTreeNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("当前二叉树为空无法遍历~~");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前二叉树为空无法遍历~~");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("当前二叉树为空无法遍历~~");
        }
    }

    /**
     * 前序查找
     * @param id
     * @return
     */
    public HeroBinaryTreeNode preSearch(int id) {
        if (root != null) {
            return root.preSearch(id);
        } else {
            return null;
        }
    }

    /**
     * 中序查找
     * @param id
     * @return
     */
    public HeroBinaryTreeNode infixSearch(int id) {
        if (root == null) {
            return null;
        } else {
            return root.infixSearch(id);
        }
    }

    /**
     * 后序查找
     * @param id
     * @return
     */
    public HeroBinaryTreeNode postSearch(int id) {
        if (root == null) {
            return null;
        } else {
            return root.postSearch(id);
        }
    }

    /**
     * 删除节点
     * 如果为叶子节点删除叶子节点，为非叶子节点直接删除子树
     * @param id
     */
    public void delNodeSimple(int id) {
        if (root == null) {
            System.out.println("二叉树为空~~");
            return;
        }
        if (root.no == id) {
            root = null;
            System.out.printf("编号为%d的已经删除", id);
            return;
        }
        root.delNodeSimple(id);
    }

}

/**
 * 水浒英雄的二叉树节点
 */
class HeroBinaryTreeNode {
    public int no;
    public String name;
    public HeroBinaryTreeNode left;
    public HeroBinaryTreeNode right;

    public HeroBinaryTreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroBinaryTreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历方法
     */
    public void preOrder() {
        // 输出父节点
        System.out.printf("%d英雄是%s\n", this.no, this.name);
        // 输出左边的树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 输出右边的树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历方法
     */
    public void infixOrder() {
        // 输出左边的树
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.printf("%d英雄是%s\n", this.no, this.name);
        // 输出右边的树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历方法
     */
    public void postOrder() {
        // 输出左边的树
        if (this.left != null) {
            this.left.postOrder();
        }
        // 输出右边的树
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父节点
        System.out.printf("%d英雄是%s\n", this.no, this.name);
    }

    /**
     * 前序查找方法
     * @param id
     * @return
     */
    public HeroBinaryTreeNode preSearch(int id) {
        // 先判断父节点是否为要查找的节点
        if (this.no == id) {
            return this;
        }
        HeroBinaryTreeNode res = null; // 最终的结果
        // 判断左节点是否为空，不为空继续向下查找
        if (this.left != null) {
            res = this.left.preSearch(id);
        }
        // 找到了就返回res
        if (res != null) {
            return res;
        }
        // 判断右节点是否为空，不为空继续向下查找
        if (this.right != null) {
            res = this.right.preSearch(id);
        }
        // 找到了就返回res
        // res为null，证明左右和自己都不是，直接返回res
        return res;
    }

    /**
     * 中序查找方法
     * @param id
     * @return
     */
    public HeroBinaryTreeNode infixSearch(int id) {
        HeroBinaryTreeNode res = null;
        //先判断左节点是否为空，不为空继续向下查找
        if (this.left != null) {
            res = this.left.infixSearch(id);
        }
        // 左边找到了，直接返回
        if (res != null) {
            return res;
        }
        // 判断父节点是否为要查找的节点
        if (this.no == id) {
            return this;
        }
        // 判断右节点是否为空，不为空继续向下查找
        if (this.right != null) {
            res = this.right.infixSearch(id);
        }
        // res为null，则该节点自己和左右的树都没有要找的，返回null即res
        return res;
    }

    /**
     * 后序查找方法
     * @param id
     * @return
     */
    public HeroBinaryTreeNode postSearch(int id) {
        HeroBinaryTreeNode res = null;
        //先判断左节点是否为空，不为空继续向下查找
        if (this.left != null) {
            res = this.left.infixSearch(id);
        }
        // 左边找到了，直接返回
        if (res != null) {
            return res;
        }
        // 判断右节点是否为空，不为空继续向下查找
        if (this.right != null) {
            res = this.right.infixSearch(id);
        }
        // 右边找到，直接返回
        if (res != null) {
            return res;
        }
        // 判断父节点是否为要查找的节点
        if (this.no == id) {
            return this;
        }
        // res为null，则该节点自己和左右的树都没有要找的，返回null即res
        return null;
    }

    /**
     * 删除节点
     * 如果为叶子节点删除叶子节点，为非叶子节点直接删除子树
     * @param id
     */
    public void delNodeSimple(int id) {
        if (this.left != null && this.left.no == id) {
            this.left = null;
            System.out.printf("编号为%d的已删除", id);
            return;

        }
        if (this.right != null && this.right.no == id) {
            this.right = null;
            System.out.printf("编号为%d的已删除", id);
            return;
        }

        if (this.left != null) {
            this.left.delNodeSimple(id);
        }
        if (this.right != null) {
            this.right.delNodeSimple(id);
        }
    }

    /**
     * 删除节点
     * 如果为叶子节点删除叶子节点，为非叶子节点直接删除子树
     * @param id
     */
    public void delNode(int id) {
        if (this.left != null && this.left.no == id) {
            System.out.printf("编号为%d的已删除", id);
            // 叶子节点直接删除
            if (this.left.left == null && this.left.right == null) {
                this.left = null;
                return;
            } else {
                // 非叶子节点
                HeroBinaryTreeNode temp = null;
                if (this.left.left == null) {
                    this.left = this.left.right;
                } else if (this.left.right == null) {
                    this.left = this.left.left;
                } else {
                    temp = this.left.right;
                    this.left.left.preOrder();
                }
            }

        }
        if (this.right != null && this.right.no == id) {
            this.right = null;
            System.out.printf("编号为%d的已删除", id);
            return;
        }

        if (this.left != null) {
            this.left.delNodeSimple(id);
        }
        if (this.right != null) {
            this.right.delNodeSimple(id);
        }
    }
}
