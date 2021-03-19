import java.util.ArrayList;
import java.util.Arrays;

/**
 * 普利姆和克鲁斯卡尔算法
 * 解决修路问题
 */
public class PrimAndKruskalAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        // 10000表示两个点不连通
        // 两个点不连通一般是使用int值的最大值即Integer.MAX_VALUE来表示，这里为了简单，用10000代替。
        int[][] matrix = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        Graph graph = new Graph(vertex, matrix);
        MiniTree miniTree = new MiniTree();
        miniTree.kruskal(graph);
    }
}

/**
 * 村庄的无向网
 */
class Graph {

    // 存储顶点信息
    public char[] vertex;
    // 存储图的邻接矩阵
    public int[][] matrix;
    // 存储边信息的数组
    public EData[] edges;
    // 存储边的数量
    public int numOfEdges;
    // 顶点是否被访问过
    public boolean[] isVisited;

    public Graph(char[] vertex, int[][] matrix) {

        int n = vertex.length;

        // 初始化顶点
        this.vertex = new char[n];
        for (int i = 0; i < n; i++) {
            this.vertex[i] = vertex[i];
        }

        // 初始化顶点访问状态数组
        this.isVisited = new boolean[n];

        // 初始化邻接矩阵
        this.matrix = new int[n][n];
        int num = 0; // 存储节点之间连通的数量
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (matrix[i][j] != 10000) {
                    num++;
                }
            }
        }
        // 初始化边的数量
        this.numOfEdges = num / 2; // 要特别注意，矩阵表示的是双向的连通，因此边的数量应该为节点之间连通数量的一半

        // 初始化存储边信息的数组
        this.edges = new EData[numOfEdges];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] != 10000) {
                    EData eData = new EData(i, j, matrix[i][j]);
                    this.edges[index] = eData;
                    index++;
                }
            }
        }
    }
}

/**
 * 村庄的最小生成树
 */
class MiniTree {

    /**
     * 通过普利姆算法获取村庄的最小生成树
     * @param graph 村庄的无向网
     * @param v 开始节点
     */
    public void prim(Graph graph, int v) {
        // 标记开始节点为已经访问过
        graph.isVisited[v] = true;

        int minWeight = 10000; // 存储最小边的权重，初始值一定要设置成一个很大的数
        int v1 = 0; // 存储最小边的第一个顶点
        int v2 = 0; // 存储最小边的第二个顶点
        // 村庄的n个顶点经过prim算法计算后，一定会得到n-1条边
        for (int i = 1; i < graph.vertex.length; i++) {

            for (int j = 0; j < graph.vertex.length; j++) {
                for (int k = 0; k < graph.vertex.length; k++) {
                    if (graph.isVisited[j] && !graph.isVisited[k]) {
                        // 如果j顶点被访问过，j顶点没被访问过，判断i-j边的权重
                        if (graph.matrix[j][k] < minWeight) {
                            // 如果i-j边权重小于上一个最小的边，则重新赋值minWeight，并且记录i,j
                            minWeight = graph.matrix[j][k];
                            v1 = j;
                            v2 = k;
                        }
                    }
                }
            }

            // 上述两个for循环计算出的v1,v2,minWeight一定是最小权重的边
            // 打印权重最小的边和其权重
            System.out.println(v1 + "-" + v2 + ":" + minWeight);
            // 将v2标记为已经访问
            graph.isVisited[v2] = true;
            // 重新初始化minWeight = 10000好进入下次循环
            minWeight = 10000;
        }
    }

    /**
     * 通过克鲁斯卡尔算法求村庄的最小生成树
     * @param graph
     */
    public void kruskal(Graph graph) {
        // 最终边的数组
        EData[] res = new EData[graph.vertex.length - 1];
        int[] ends = new int[graph.vertex.length];

        // 将边的集合按照权值从小到大排序
        sortEdges(graph.edges);
        EData[] edges = graph.edges;

        // 遍历边的集合，判断边是否构成回路，如果没有则加入；否则不能加入
        int index = 0; // 在最终入选的边的索引
        for (int i = 0; i < edges.length; i++) {
            // 获取i条边的第一个顶点
            int p1 = edges[i].start;
            // 获取i条边的第二个顶点
            int p2 = edges[i].end;
            // 获取p1的终点
            int m = getEnds(ends, p1);
            // 获取p2的终点
            int n = getEnds(ends, p2);
            // 判断p1和p2是否构成回路
            if (m != n) {
                // 不构成回路，设置m的终点是n
                ends[m]= n;
                // 将边加入集合
                res[index] = edges[i];
                index++;
            }
        }

        for (EData i:
             res) {
            System.out.println(graph.vertex[i.start] + "->" + graph.vertex[i.end]);
        }
    }

    /**
     * 根据传入的边的信息数组，进行排序
     * @param edges
     */
    public void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].weight > edges[j].weight) {
                    EData temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }
    }

    /**
     * 根据传入的节点坐标v，获取其对应的终点坐标
     * @param ends
     * @param v
     * @return
     */
    public int getEnds(int[] ends, int v) {
        // 该while循环很巧妙
        while (ends[v] != 0) {
            v = ends[v];
        }
        return v;
    }
}

/**
 * 存储边的信息
 */
class EData {
    int start; // 存储边的一端
    int end; // 存储边的另一端
    int weight; // 存储边的权重

    public EData(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
