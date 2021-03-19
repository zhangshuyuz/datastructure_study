public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        // 10000表示两个点不连通
        // 两个点不连通一般是使用int值的最大值即Integer.MAX_VALUE来表示，这里为了简单，用10000代替。
        final int INF = 65535;
        int[][] matrix = {
                {INF, 5, 7, INF, INF, INF, 2},
                {5, INF, INF, 9, INF, INF, 3},
                {7, INF, INF, INF, 8, INF, INF},
                {INF, 9, INF, INF, INF, 4, INF},
                {INF, INF, 8, INF, INF, 5, 4},
                {INF, INF, INF, 4, 5, INF, 6},
                {2, 3, INF, INF, 4, 6, INF}
        };

        DijGraph dij = new DijGraph(vertex, matrix);
        dijkstra(dij, 0);
    }

    /**
     * 以index为下标的节点作为初始节点，使用迪杰斯特拉算法求最短路径
     * @param dij
     * @param index
     */
    public static void dijkstra(DijGraph dij, int index) {
        VisitedVertex vv = new VisitedVertex(dij.vertex.length, index); // 以index作为出发节点创建节点集合
        update(index, dij, vv); // 更新初始节点周围节点的距离和周围节点的前驱结点
        for (int i = 1; i < vv.isVisited.length; i++) {
            index = vv.updateNext(); // 更新出发节点
            update(index, dij, vv); // 更新出发节点到周围节点的距离和周围节点的前驱结点
        }
        // 验证算法
        for (int i :
                vv.dis) {
            System.out.println(i);
        }
    }

    /**
     * 更新以index节点为出发节点，到周围节点的距离和周围节点的前驱结点
     * @param index
     * @param dij 图
     * @param vv 顶点集合
     */
    public static void update(int index, DijGraph dij, VisitedVertex vv) {
        int len = 0; // 定义一个变量用来存储上一个出发节点到index的距离和index到周围节点的距离和
        // 遍历index的周围节点
        for (int i = 0; i < dij.matrix[index].length; i++) {
            if (!vv.isVisited(i)) {
                // 如果i节点没有被访问过
                len = vv.getDis(index) + dij.matrix[index][i]; // 存储距离和
                if (len < vv.getDis(i)) {
                    // 如果距离和更小
                    vv.updatePre(i, index); // 更新i的前驱
                    vv.updateDis(i, len); // 更新初始节点到i的最短路径
                }
            }
        }
    }
}

class DijGraph {
    public char[] vertex;
    public int[][] matrix;

    public DijGraph(char[] vertex, int[][] matrix) {
        this.vertex = new char[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        this.matrix = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }
}

/**
 * 访问顶点集合
 */
class VisitedVertex {

    public int[] isVisited;
    public int[] dis; // 初始节点到每个节点的最短距离
    public int[] preNode; // 每个节点的前驱节点下标集合。用来做最终回溯的

    /**
     * 构造器中传入节点数量，初始节点下标
     * @param length 节点数量
     * @param index 初始节点下标
     */
    public VisitedVertex(int length, int index) {

        // 初始化每个节点的访问状态
        this.isVisited = new int[length];
        this.isVisited[index] = 1; // 将初始节点置为1，表示已经访问过

        // 初始化每个节点到初始节点的距离
        this.dis = new int[length];
        for (int i = 0; i < length; i++) {
            if (i == index) {
                dis[i] = 0; // 初始节点和自己肯定是0
            } else {
                dis[i] = 65535; // 这时候算法未开始，初始节点和其他节点最小距离置为最大，这里用65535表示
            }
        }

        // 初始化每个节点的前驱节点
        this.preNode = new int[length];
    }

    // 需要的基础方法
    /**
     * 判断一个节点是否被访问过
     * @param index 待判断节点下标
     * @return
     */
    public boolean isVisited(int index) {
        return isVisited[index] == 1;
    }

    /**
     * 更新下标为index的节点到出发节点的距离
     * @param index
     * @param len
     * @return
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新下标为index的节点的前驱节点为pre
     * @param index
     * @param pre
     */
    public void updatePre(int index, int pre) {
        preNode[index] = pre;
    }

    /**
     * 返回index对应的节点到出发节点的距离
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }



    /**
     * 在未访问节点中，选择下一个顶点作为出发节点，并将该节点置为已经访问
     * @return
     */
    public int updateNext() {
        int min = 65535; // 用来跟没有被访问过的结点的路径做比较，存储最短路径的值，因此初始化为最大65535
        int n = 0; // 用来记录最短路径的节点下标
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i] != 1) {
                // 寻找没有被访问过的节点
                if (dis[i] < min) {
                    // 寻找路径最短的节点
                    min = dis[i];
                    n = i;
                }
            }
        }
        // 经过上述循环，得到的n为未访问节点中，路径最短的节点的下标
        isVisited[n] = 1;
        return n;
    }

}
