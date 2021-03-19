import java.io.*;
import java.util.ArrayList;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        /**
         * 定义一个6 * 6的棋盘，人为制造一场对局，并打印
         */
        int[][] arr = new int[6][6];
        arr[2][4] = 1;
        arr[3][5] = 2;
        arr[2][5] = 1;
        arr[3][4] = 2;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.printf("%d\t", arr[i][j]);
            }
            System.out.printf("\n");
        }
        /**
         * 将该棋盘存入一个稀疏数组，然后将稀疏数组存储到文件
         */
        int sum = 0; //获取棋盘中棋子总数
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] sprseArr = new int[sum + 1][3];
        sprseArr[0][0] = 6;
        sprseArr[0][1] = 6;
        sprseArr[0][2] = sum;
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    sprseArr[count][0] = i;
                    sprseArr[count][1] = j;
                    sprseArr[count][2] = arr[i][j];
                }
            }
        }
        System.out.println("稀疏数组");
        for (int[] i :
                sprseArr) {
            for (int j :
                    i) {
                System.out.printf("%d\t", j);
            }
            System.out.println();
        }
        File out = new File("XiShuShuZu/data.txt");
        FileWriter fw = new FileWriter(out);
        for (int[] a :
                sprseArr) {
            for (int data :
                    a) {
                fw.write(data + "\r\n");
            }
        }
        fw.flush();
        fw.close();
    }
}
