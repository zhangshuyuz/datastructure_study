import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 骑士周游问题使用贪心算法进行优化
 */
public class RiderQuestionOptimize {
    private static int X = 6; // 表示棋盘列
    private static int Y = 6; // 表示棋盘行
    private static boolean finished; // 表示马是否走完棋盘的全部格子，默认为true

    public static void main(String[] args) {

        RiderGraph graph = new RiderGraph(X);
//        Point first = new Point();
//        first.x = 5;
//        first.y = 5;
        traversalChessboard(graph, 2, 3, 1);
        for (int[] i :
                graph.matrix) {
            for (int j :
                    i) {
                System.out.printf("%d\t", j);
            }
            System.out.println();
        }
    }

    /**
     * 计算棋子还可以走哪些位置
     * Point类为Java自带的类，用来表示一个点
     * @param curPoint
     */
    public static ArrayList<Point> getNext(Point curPoint) {
        // 创建集合
        ArrayList<Point> points = new ArrayList<>();
        // 创建Point，保存不同走法的引用
        Point p = new Point();

        // 注意，每次add添加都必须new个point，否则永远是同一个引用
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            // 表示棋子可以向左走2，向上走1
            // 根据走法创建对应的点，加入集合
            points.add(new Point(p));
        }

        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < X) {
            // 表示棋子可以向左走2，向下走1
            points.add(new Point(p));
        }

        if ((p.x = curPoint.x + 2) < Y && (p.y = curPoint.y - 1) >= 0) {
            // 表示棋子可以向右走2，向上走1
            points.add(new Point(p));
        }

        if ((p.x = curPoint.x + 2) < Y && (p.y = curPoint.y + 1) < X) {
            // 表示棋子可以向右走2，向下走1
            points.add(new Point(p));
        }

        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            // 表示棋子可以向左走1，向上走2
            points.add(new Point(p));
        }

        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < X) {
            // 表示棋子可以向左走1，向下走1
            points.add(new Point(p));
        }

        if ((p.x = curPoint.x + 1) < Y && (p.y = curPoint.y - 2) >= 0) {
            // 表示棋子可以向右走1，向上走2
            points.add(new Point(p));
        }

        if ((p.x = curPoint.x + 1) < Y && (p.y = curPoint.y + 2) < X) {
            // 表示棋子可以向右走1，向下走1
            points.add(new Point(p));
        }

        // 返回棋子可以走的集合
        return points;
    }

    /**
     * 遍历棋盘
     * @param graph
     * @param row
     * @param column
     * @param step
     */
    public static void traversalChessboard(RiderGraph graph, int row, int column, int step) {
        // 标记第row行，第column列为第step步走过
        graph.matrix[row][column] = step;
        // 标记第row行第column列的格子被访问过，被访问过记为true
        graph.isVisited[X * row + column] = true;

        // 获取当前点可以走的下一个位置的集合
        ArrayList<Point> points = getNext(new Point(column, row));

        /*
        获取到当前点可走的下一个位置集合后，贪心算法优化
         */
        sort(points);

        // 遍历points
        while (!points.isEmpty()) {
            // 每次取出一个可以走的位置
            Point remove = points.remove(0);
            // 判断这个可以走的位置是否被访问过
            // 在二维图像中，y轴坐标表示矩阵的行，x轴坐标表示矩阵的列
            if (!graph.isVisited[remove.y * X + remove.x]) {
                // 没有被访问过
                // 继续遍历
                traversalChessboard(graph, remove.y, remove.x, step + 1);
            }
        }

        // 判断马是否走过了整个棋盘
        // 不能只靠step来判断是否走完整个棋盘
        // step<X*Y有两种状态：1.没有走完棋盘 2.处于回溯状态；因此必须还要靠一个finished属性来判断
        if (step < X * Y && !finished) {
            // 马没有走完棋盘
            // 将本格子重新赋值为0
            graph.matrix[row][column] = 0;
            // 将本格子重新置为未访问
            graph.isVisited[row * X + column] = false;
        } else {
            // 马走完了棋盘
            // 标记棋盘已经走完
            finished = true;
        }
    }

    /**
     * 对下一个位置的集合，按照元素的下一个位置数量非递减排序
     * @param points
     */
    public static void sort(ArrayList<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取o1下一个位置集合大小
                int num1 = getNext(o1).size();
                // 获取o2下一个位置集合大小
                int num2 = getNext(o2).size();
                // 非递减排序
                if (num1 < num2) {
                    return -1;
                } else if (num1 == num2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}

/**
 * 跳马问题棋盘图
 */
class RiderGraphOptimize {
    int[][] matrix;
    // 记录格子是否被访问过，不用使用二维数组，将二维数组的思维转化为一维数组即可
    boolean[] isVisited;

    public RiderGraphOptimize(int col) {
        this.matrix = new int[col][col];
        this.isVisited = new boolean[col * col];
    }
}
