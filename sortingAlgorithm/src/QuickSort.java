import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {

//        int[] arr = new int[800000];
//        for (int i = 0; i < 800000; i++) {
//            arr[i] = (int)(Math.random() * 800000);
//        }
//        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date dat1 = new Date();
//        System.out.println(df.format(dat1));


        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int n = Integer.parseInt(num);
        int[] s = new int[n];

        String line = sc.nextLine();
        String[] strings = line.split(" ");

        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(strings[i]);
        }

        quick(s, 0, s.length - 1);

        for (int j:
                s) {
            System.out.printf("%d ", j);
        }




//        Date dat2 = new Date();
//        System.out.println(df.format(dat2));
////        for (int i :
////                arr) {
////            System.out.printf("%d\t", i);
////        }
    }


    /**
     * 快速排序
     * @param arr
     * @param left 左下标
     * @param right 右下标
     */
    public static void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int temp = 0;
        int pivot = arr[(left + right) / 2];
        // 当左边指针小于右边指针就一直运行
        while (l < r) {
            // 左指针持续向右移动，找出左边小于基数的值的坐标
            while (arr[l] < pivot) {
                l++;
            }
            // 右指针持续向左移动，找出右边大于基数的值得坐标
            while (arr[r] > pivot) {
                r--;
            }

            // 如果此时左边指针已经大于右边指针，说明基数左边的所有元素已经全部小于基数右边的所有元素，退出循环
            // 否则交换值
            if (l >= r) {
                break;
            } else {
                // 交换找到的值
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }

            // 下面两个判断是防止左右两边都有arr[l] == pivot, arr[r] == pivot而导致的死循环
            if (arr[l] == pivot) {
                r--;
            }

            if (arr[r] == pivot) {
                l++;
            }
        }

        if (l == r) {
            r--;
            l++;
        }
        // 左边有两个及以上元素，进入递归
        if (left < r) {
            quick(arr, left, r);
        }
        // 右边有两个及以上元素，进入递归
        if (right > l) {
            quick(arr, l, right);
        }
    }
}



