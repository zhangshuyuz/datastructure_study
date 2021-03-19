import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

    private static int[] bubble = null;

    public static void main(String[] args) {
        bubble = new int[80000];
        for (int i = 0; i < 80000; i++) {
            bubble[i] = (int) (Math.random() * 80000);
        }
        Date dat1 = new Date();
        DateFormat datFor1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(datFor1.format(dat1));
        sort();
        Date dat2 = new Date();
        System.out.println(datFor1.format(dat2));
    }


    /**
     * 冒泡排序
     */
    public static void sort() {

        int temp = 0;
        // 优化，加入flag
        boolean flag = false;
        for (int i = bubble.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (bubble[j] < bubble[j + 1]) {
                    temp = bubble[j];
                    bubble[j] = bubble[j + 1];
                    bubble[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            } else {
                // 重置
                flag = true;
            }
        }
    }
}
