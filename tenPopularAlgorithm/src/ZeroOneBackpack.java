import java.util.Map;

/**
 * 01背包问题
 */
public class ZeroOneBackpack {
    public static void main(String[] args) {
        // 定义背包大小
        int size = 5;
        // 定义物品和物品的价格和物品的重量
        String[] things = {"手机", "平板", "台式"};
        int[] prize = {1500, 2000, 3000};
        int[] weight = {1, 4, 3};
        solveByArray(size, things, prize, weight);
    }

    /**
     * 背包问题
     * 数组作为动态规划表
     * @param size 背包大小
     * @param things 物品名数组
     * @param prize 物品价格数组
     * @param weight 物品质量数组
     */
    public static void solveByArray(int size, String[] things, int[] prize, int[] weight) {
        // 初始化动态规划表
        int[][] table = new int[things.length + 1][size + 1];
        // 因为要记录背包中加入的物品，初始化一个记录的表
        boolean[][] thing = new boolean[things.length + 1][size + 1];

        // 进行动态规划
        for (int i = 1; i < things.length + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                if (weight[i - 1] > j) {
                    // 因为程序的i是从1开始，因此物品质量和物品价格和物品的下标都是从i-1
                    // 如果动态背包容量j小于物品质量
                    table[i][j] = table[i - 1][j];
                } else {
                    // 如果动态背包容量j大于等于物品质量
                    if (table[i - 1][j] < table[i - 1][j - weight[i - 1]] + prize[i - 1]) {
                        // 如果加入该物品符合在j容量下的最优策略，将最优策略写入
                        table[i][j] = table[i - 1][j - weight[i - 1]] + prize[i - 1];
                        // 将该i-1对应的物品加入j容量的背包
                        thing[i][j] = true;
                    } else {
                        // 如果加入该物品不符合最优策略
                        table[i][j] = table[i - 1][j];
                    }
                }
            }
        }

        //动态规划完毕，输出最终背包里的物品
        int i = things.length; // 动态规划表最大行
        int j = size; // 动态规划表最大列
        while (i > 0 && j > 0) {
            if (thing[i][j]) {
                System.out.printf("%s加入背包\n", things[i - 1]);
                j = j - weight[i - 1];
            }
            i--;
        }
    }

    public static void solveByHashTable() {

    }
}
