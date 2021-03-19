import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 7, 9, 12, 23, 65, 956, 1025, 2365};
        int i = fiboSearch(arr, 9);
        System.out.println(i);
    }

    /**
     * 非递归方法完成斐波那契查找
     * @param arr
     * @param findValue
     * @return
     */
    public static int fiboSearch(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0; // 斐波那契数列的下标，从第一个开始
        int mid = 0;
        int[] fib = fibonacci();

        // 找到最接近数组长度的斐波那契数列的值
        while (right > fib[k] - 1) {
            k++;
        }
        // 扩充数组使之长度等于k对应的斐波那契数列的值
        // 克隆原来的数组，长度等于斐波那契数列的值，不够的用0填充
        int[] temp = Arrays.copyOf(arr, fib[k]);
        // 用arr数组的最后元素填充新数组的扩容的元素
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        // 对新数组使用斐波那契查找
        while (left <= right) {
            mid = left + fib[k - 1] - 1;
            // 如果查找的数小于mid对应的数
            if (findValue < temp[mid]) {
                // 向前查找
                right = mid - 1;
                // 将前一个部分当做新斐波那契数列的值
                k = k - 1;
            } else if (findValue > temp[mid]){
                left = mid + 1;
                // 将后一个部分当做新斐波那契数列的值
                k = k - 2;
            } else {
                // 因为该数组是经过填充的，因此需要判断找到的值的下标
                if (mid < right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }

    /**
     * 获取斐波那契数列
     * @return
     */
    public static int[] fibonacci() {
        // 获取长度为20的斐波那契数列
        int k = 0;
        int[] fib = new int[20];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < 20; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }
}
