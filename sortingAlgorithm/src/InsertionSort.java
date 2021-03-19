import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertionSort {

    private static int[] insertion = null;

    public static void main(String[] args) {
        insertion = new int[80000];
        for (int i = 0; i < 80000; i++) {
            insertion[i] = (int) (Math.random() * 80000);
        }
        Date dat1 = new Date();
        DateFormat datFor1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(datFor1.format(dat1));
        insertion();
        Date dat2 = new Date();
        System.out.println(datFor1.format(dat2));
    }

    /**
     * 插入排序
     */
    public static void insertion() {
        int temp = 0;
        for (int i = 1; i < insertion.length; i++) {
            temp = insertion[i];
            for (int j = i - 1; j >= 0; j--) {
                if (temp > insertion[j]) {
                    insertion[j + 1] = insertion[j];
                } else {
                    insertion[j] = temp;
                }
            }
        }
    }
}
