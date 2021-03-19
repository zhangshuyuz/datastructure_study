public class EightQueens {
    public static void main(String[] args) {
        EightQueensSolve queens = new EightQueensSolve();
        queens.eightQueen(0);
    }
}

/**
 * 解决八皇后问题
 */
class EightQueensSolve {

    public int[] solve = new int[8];
    private int temp = 0;

    /**
     * 判断第n个皇后和前面的皇后是否满足八皇后问题的条件
     * @param n
     * @return
     */
    public boolean judge (int n) {
        for (int i = 0; i < n; i++) {
            // 判断是否在同一列，或同一斜线
            if (solve[i] == solve[n] || Math.abs(n - i) == Math.abs(solve[n] - solve[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 放置皇后
     * 分解放置问题，就是在每一行都试一次，从第一个列开始，如果有一个情况和之前的皇后都满足八皇后问题，
     * 则继续向后放置，否则，验证下一个列，直到验证完全部的列。结束递归条件就是第八行的皇后也放置了
     * @param n
     */
    public void eightQueen(int n) {
        if (n == 8) {
            // 说明八个皇后已经放好了，这个时候要结束递归了
            temp++;
            for (int i = 0; i < 8; i++) {
                System.out.printf("%d\t", solve[i]);
            }
            System.out.printf("第%d个解法", temp);
            System.out.println();
            return;
        }
        // 放入皇后，并判断是否冲突
        for (int i = 0; i < 8; i++) {
            // 放入皇后
            solve[n] = i;

            // 判断是否满足八皇后问题
            if (judge(n)) {
                // 满足的话继续向后放入第 n + 1 个皇后
                eightQueen(n + 1);
            }
        }
        // 不满足回溯到满足的那一步
    }
}
