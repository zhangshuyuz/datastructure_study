import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 7, 9, 9, 9, 9, 9, 9, 9, 9, 12, 23, 65, 956, 1025, 2365, 6956};
        List<Integer> index = binary(arr, 0, arr.length - 1, 3);
        for (Integer i :
                index) {
            System.out.println("index=" + i);
        }
    }

//    /**
//     * 二分查找
//     * @param arr
//     * @param left
//     * @param right
//     * @param findValue
//     * @return
//     */
//    public static int binary(int[] arr, int left, int right, int findValue) {
//        // 如果数组中没有要查找的元素，则结束递归，返回-1
//        if (left > right) {
//            return -1;
//        }
//
//        int midIndex = (left + right) / 2; // 获取中间值得坐标
//        int mid = arr[midIndex]; // 获取中间值
//
//        if (findValue > mid) {
//            return binary(arr, midIndex + 1, right, findValue);
//        } else if (findValue < mid) {
//            return binary(arr, left, midIndex - 1, findValue);
//        } else {
//            return midIndex;
//        }
//    }

    /**
     * 二分查找
     * 如果查找的值在数组中有多个，则把所有值的下标都拿到
     * @param arr
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    public static List binary(int[] arr, int left, int right, int findValue) {
        // 如果数组中没有要查找的元素，则结束递归，返回-1
        if (left > right ) {
            return new ArrayList<Integer>();
        }

        int midIndex = (left + right) / 2; // 获取中间值得坐标
        int mid = arr[midIndex]; // 获取中间值

        if (findValue > mid) {
            return binary(arr, midIndex + 1, right, findValue);
        } else if (findValue < mid) {
            return binary(arr, left, midIndex - 1, findValue);
        } else {
            // 找到了值，就创建一个集合存放值得坐标;
            List<Integer> value = new ArrayList<>();
            value.add(midIndex);
            // 查找左边有无相同的值
            int l = midIndex - 1;
            while (true) {
                // 左边没有相同的值，或者左边没有值了退出
                if (l < 0 || arr[l] != findValue) {
                    break;
                }
                value.add(l);
                l = l - 1;
            }
            // 查找右边有无相同的值
            int r = midIndex + 1;
            while (true) {
                // 右边没有相同的值，或者右边没有值了退出
                if (r > arr.length - 1 || arr[r] != findValue) {
                    break;
                }
                value.add(r);
                r = r + 1;
            }
            return value;
        }
    }
}
