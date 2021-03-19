import java.util.ArrayList;

/**
 * 通过邻接矩阵来存储图信息
 */
public class GraphByArray {

    // 存储图中所有顶点
    private ArrayList<String> vertex;
    // 存储图中所有边个数
    private int numOfEdges;
    // 邻接矩阵存储图中边的信息
    private int[][] edges;
    // 定义一个记录节点是否被访问过的标记数组
    boolean[] isVisited;
    // 定义一个数组作为广度查询的队列
    int[] vertexQueue;

    public static void main(String[] args) {
        String[] graph = {"A", "B", "C", "D", "E", "F", "G"};
        GraphByArray gba = new GraphByArray(graph.length);
        for (String i :
                graph) {
            gba.insertVertex(i);
        }
        gba.insertEdges(0, 1, 1);
        gba.insertEdges(0, 2, 1);
        gba.insertEdges(1, 3, 1);
        gba.insertEdges(1, 4, 1);
        gba.insertEdges(3, 6, 1);
        gba.insertEdges(4, 6, 1);
        gba.insertEdges(2, 5, 1);

        gba.breadthFirstOrder();
    }

    /**
     * 构造器初始化顶点个数
     * @param n
     */
    public GraphByArray(int n) {
        this.vertex = new ArrayList<>(n);
        // 根据顶点个数初始化邻接矩阵
        this.edges = new int[n][n];
        // 初始化边的数量
        this.numOfEdges = 0;
        // 初始化节点的标记数组
        this.isVisited = new boolean[n];
        // 初始化广度查询需要的队列
        this.vertexQueue = new int[n];
    }

    /**
     * 添加顶点
     * @param v
     */
    public void insertVertex(String v) {
        vertex.add(v);
    }

    /**
     * 添加边
     * @param v1 顶点下标，“A”->0, "B"->1
     * @param v2 顶点下标，“A”->0, "B"->1
     * @param weight 权重，1表示顶点之间连通
     */
    public void insertEdges(int v1, int v2, int weight) {
        // 设置两个顶点的连通状态
        // 因为是无向图，互相状态都要设置
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        // 边计数增加
        numOfEdges++;
    }

    // 图中常用的方法
    /**
     * 返回节点的个数
     */
    public int getNumOfVertex() {
        return this.vertex.size();
    }

    /**
     * 返回边的个数
     */
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    /**
     * 返回顶点下标对应的数据
     * @param index
     * @return
     */
    public String getVertexByIndex(int index) {
        return this.vertex.get(index);
    }

    /**
     * 返回顶点v1、v2的连接状态
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return this.edges[v1][v2];
    }

    /**
     * 显示邻接矩阵
     */
    public void show() {
        for (int[] i :
                this.edges) {
            for (int j :
                    i) {
                System.out.printf("%d\t", j);
            }
            System.out.println();
        }
    }

    /**
     * 获取第一个相邻节点的下标
     * @param v1 初始节点下标
     * @return
     */
    public int getFirstNeighbor(int v1) {
        for (int i = v1 + 1; i < vertex.size(); i++) {
            if (edges[v1][i] > 0) {
                // i这个节点和v1连通，即i为v1相邻节点
                return i;
            }
        }
        // 没有相邻节点
        return -1;
    }

    /**
     * 根据上一个相邻节点的下标，获取下一个相邻节点
     * @param v1 初始节点下标
     * @param v2 上一个相邻节点下标
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertex.size(); j++) {
            if (edges[v1][j] > 0) {
                // 初始节点和j节点连通，说明j为下一个相邻节点
                return j;
            }
        }
        // 没有下一个相邻节点
        return -1;
    }

    /**
     * 深度优先遍历
     * @param isVisited 结点是否被访问
     * @param v1 初始结点下标
     */
    private void deepFirstOrder(boolean[] isVisited, int v1) {
        // 输出结点信息
        System.out.print(getVertexByIndex(v1) + "->");
        // 将节点设为被访问过
        isVisited[v1] = true;

        // 获取第一个相邻结点下标
        int v2 = getFirstNeighbor(v1);
        while (v2 > 0) {
            // 如果相邻节点存在
            if (!isVisited[v2]) {
                // 如果相邻节点没有被访问过，将下一个结点作为初始节点
                deepFirstOrder(isVisited, v2);
            }
            // 如果相邻节点被访问过，获取下一个相邻节点直到没有相邻节点
            v2 = getNextNeighbor(v1, v2);
        }
        // 没有第一个相邻节点，结束，进入下一个结点的深度遍历
    }

    public void deepFirstOrder() {
        // 深度遍历所有的节点
        // 如果每个有某些节点不连通，该循环是为了让不连通图也遍历到
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                deepFirstOrder(isVisited, i);
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public void breadthFirstOrder() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                breadthFirstOrder(isVisited, i);
            }
        }
    }
    
    public void breadthFirstOrder(boolean[] isVisited, int v) {
        // 初始化队列尾指针和头指针
        int last = 0;
        int first = 0;

        // 打印初始节点信息
        System.out.print(getVertexByIndex(v) + "->");
        // 将初始节点置为被访问过
        isVisited[v] = true;
        // 初始节点入队列
        vertexQueue[last] = v;
        last++;

        // 如果队列不为空
        while (last != first) {
            // 队列头元素出队列
            int v1 = vertexQueue[first];
            vertexQueue[first] = 0;
            first++;

            // 获取第一个邻接节点
            int firstNeighbor = getFirstNeighbor(v);
            while (firstNeighbor > 0) {
                // 邻接节点存在
                if (!isVisited[firstNeighbor]) {
                    // 邻接节点没有被访问过
                    System.out.print(getVertexByIndex(firstNeighbor) + "->");
                    isVisited[firstNeighbor] = true;
                    // 邻接节点入队列
                    vertexQueue[last] = firstNeighbor;
                    last++;
                }
                // 获取下一个邻接节点
                firstNeighbor = getNextNeighbor(v, firstNeighbor);
            }
        }
    }
}
