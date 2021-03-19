public class FloydAlgorithm {
    private static final int INF = 65535;
    public static void main(String[] args) {
        char[] vertex = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        // 10000表示两个点不连通
        // 两个点不连通一般是使用int值的最大值即Integer.MAX_VALUE来表示，这里为了简单，用10000代替。
        int[][] matrix = {
                {INF, 5, 7, INF, INF, INF, 2},
                {5, INF, INF, 9, INF, INF, 3},
                {7, INF, INF, INF, 8, INF, INF},
                {INF, 9, INF, INF, INF, 4, INF},
                {INF, INF, 8, INF, INF, 5, 4},
                {INF, INF, INF, 4, 5, INF, 6},
                {2, 3, INF, INF, 4, 6, INF}
        };

        FloydGraph graph = new FloydGraph(vertex, matrix);
        floyd(graph);
    }

    /**
     * 根据传入的图，用弗洛伊德算法求每个节点直接的最短路径
     * @param graph
     */
    public static void floyd(FloydGraph graph) {
        int len = 0; // 记录距离
        for (int i = 0; i < graph.vertex.length; i++) {

            // 寻找以i为中继的两个节点
            for (int j = 0; j < graph.vertex.length; j++) {
                for (int k = 0; k < graph.vertex.length; k++) {
                    // 找到两节点j,k
                   len = graph.matrix[j][i] + graph.matrix[i][k]; // 求出从j出发，经过i，到达k的长度
                    if (len < graph.matrix[j][k]) {
                        // 重置matrix
                        graph.matrix[j][k] = len;
                        graph.matrix[k][j] = len;
                        // 重置前驱表
                        graph.preNode[j][k] = i;
                        graph.preNode[k][j] = i;
                    }
                }
            }

        }

        for (int i = 0; i < graph.vertex.length; i++) {
            for (int j = i + 1; j < graph.vertex.length; j++) {
                System.out.printf("%d\t", graph.matrix[i][j]);
            }
            System.out.println();
        }
    }
}

class FloydGraph {
    public char[] vertex;
    public int[][] matrix; // 存放图的邻接矩阵，之后会动态变化成存储每个节点之间最短路径的矩阵
    public int[][] preNode; // 存放每个节点的前驱节点的下标

    public FloydGraph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        // 初始化前驱结点的表
        this.preNode = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                preNode[i][j] = i; // 初始时，每个边的前驱结点都是第一个节点
            }
        }
    }
}
