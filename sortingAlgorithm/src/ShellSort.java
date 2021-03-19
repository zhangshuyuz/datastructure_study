import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {

    private static int[] shell;

    public static void main(String[] args) {
        shell = new int[80000];
        for (int i = 0; i < 80000; i++) {
            shell[i] = (int) (Math.random() * 80000);
        }
         // 初始增量为arr.length / 2
        Date dat1 = new Date();
        DateFormat datFor1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(datFor1.format(dat1));
        shell(3);
        Date dat2 = new Date();
        System.out.println(datFor1.format(dat2));
//        for (int i :
//                shell) {
//            System.out.printf("%d\t", i);
//        }
    }

    /**
     * 希尔排序
     * @param s 增量
     */
    public static void shell(int s) {
        // 对s个数组进行插入排序
        int temp = 0;

        // 每个数组
        for (int i = 0; i < s; i++) {
            // 插入排序
            for (int k = i; k < shell.length; k = k + s) {
                temp = shell[k];
                for (int l = k - s; l >= 0; l = l - s) {
                    if (temp > shell[l]) {
                        shell[l + s] = shell[l];
                    } else {
                        shell[l + s] = temp;
                    }
                }
            }
        }

        // 当s==1，算法结束，开始返回
        if (s == 1) {
            return;
        } else {
            // s变小，继续排序
            s = s / 2;
            shell(s);
        }
    }
}

