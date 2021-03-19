import java.lang.reflect.Array;
import java.util.ArrayList;

public class MiGong {
    public static void main(String[] args) {
        Labyrinth miGong = new Labyrinth();
        miGong.setWayByCelue(new Labyrinth().create(), 1, 1, 1);
        System.out.println(miGong.count[0]);
        miGong.setWayByCelue(new Labyrinth().create(), 1, 1, 2);
        System.out.println(miGong.count[1]);
        miGong.setWayByCelue(new Labyrinth().create(), 1, 1, 3);
        System.out.println(miGong.count[2]);
        miGong.setWayByCelue(new Labyrinth().create(), 1, 1, 4);
        System.out.println(miGong.count[3]);
    }
}

/**
 * 迷宫回溯
 */
class Labyrinth {

    /**
     * 显示迷宫
     * @param map
     */
    public void show(int[][] map) {
        for (int[] i :
                map) {
            for (int j:
                 i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 创建一个8*7的迷宫
     */
    public int[][] create() {
        // 创建地图
        int[][] map = new int[8][7];
        // 设墙壁为1
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 || i == 7) {
                    map[i][j] = 1;
                } else {
                    map[i][0] = 1;
                    map[i][6] = 1;
                }
                if (i == 4) {
                    map[i][1] = 1;
                    map[i][2] = 1;
                }
            }
        }
        return map;
    }

    /**
     * 使用递归回溯帮助小球从左上走到右下
     * 小球探路策略为：向下->向右->向上->向左
     * @param map 地图
     * @param i 小球y坐标
     * @param j 小球x坐标
     * @return 找到通路为真，否则为假
     */
    public boolean setWay(int[][] map, int i, int j, int c) {
        if (map[6][5] == 2) {
            // 迷宫重点被激活，回退寻找路线
            count[c]++;
            return true;
        } else {
            // 判断此坐标小球是否走过
            if (map[i][j] == 0) {
                // 小球未走过，先假设此路线坐标正确，继续寻路
                map[i][j] = 2;
                // 小球继续按照策略走
                // 小球先向下走
                if (setWay(map, i + ceLue[0], j, c)) {
                    // 向下走可以，该路线坐标正确
                    count[c]++;
                    return true;
                } else if (setWay(map, i, j + ceLue[1], c)) {
                    // 向下走不可以，判断是否可以向右走，可以向右走，该路就是通的
                    count[c]++;
                    return true;
                } else if (setWay(map, i - ceLue[0], j, c)) {
                    // 向下走和向上走都不可以，判断是否可以向右走，可以则该路为通
                    count[c]++;
                    return true;
                } else if (setWay(map, i, j - ceLue[1], c)) {
                    count[c]++;
                    return true;
                } else {
                    // 此路不通，标记此路线坐标为死路，并说明错误
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 小球走过，是墙，死路，都说明该路线坐标错误
                return false;
            }
        }
    }

    /**
     * 通过不同的策略找出最短路径
     * @return
     */
    public int[] count = new int[4];
    public int[] ceLue = new int[2];

    public void setWayByCelue(int[][] map, int i, int j, int c) {
        int k = 0;
        int l = 0;

        switch (c) {
            case 1:
                k = 1;
                l = 1;
                break;
            case 2:
                k = -1;
                l = 1;
                break;
            case 3:
                k = 1;
                l = -1;
            case 4:
                k = -1;
                l = -1;
                break;
        }
        count[c - 1] = 0;
        ceLue[0] = k;
        ceLue[1] = l;
        setWay(map, i, j, c - 1);
    }
}
