import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectionSort {

    private static int[] selection = null;

    public static void main(String[] args) {

        selection = new int[80000];
        for (int i = 0; i < 80000; i++) {
            selection[i] = (int) (Math.random() * 80000);
        }

        Date dat1 = new Date();
        DateFormat datFor1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(datFor1.format(dat1));
        selection();
        Date dat2 = new Date();
        System.out.println(datFor1.format(dat2));


    }

    /**
     * 选择排序
     */
    public static void selection() {
        int temp = 0;
        for (int i = 0; i < selection.length - 1; i++) {
            for (int j = i + 1; j < selection.length; j++) {
                if (selection[i] < selection[j]) {
                    temp = selection[i];
                    selection[i] = selection[j];
                    selection[j] = temp;
                }
            }
        }
    }
}


