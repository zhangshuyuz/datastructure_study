import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }

        // 开始堆排序
        int temp = 0;
        // 从最后一个非叶子节点开始调整，将无序列表整个变成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 交换
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 因为堆顶变动了，因此需要调整这个大顶堆
            adjustHeap(arr, 0, j);
        }

    }

    /**
     * 调整局部树为一个堆
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 先将当前元素存起来
        int temp = arr[i];
        // 调整
        // 遵循从左向右，k为要与arr[i]比较的节点的索引
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 如果有右子节点，并且右子节点值大于左子节点，让k指向右子节点
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k = k + 1;
            }
            // 比较arr[k]和最初的arr[i]即temp的大小
            if (arr[k] > temp) {
                // 将子节点的值赋给本节点
                arr[i] = arr[k];
                // 将k节点作为本节点继续循环
                i = k;
            } else {
                // 因为是从下往上遍历的非叶子节点，因此下面的一定排好了序，因此可以直接退出循环
                break;
            }
        }

        // 最后将原来的arr[i]即temp放到现在调整后的位置
        arr[i] = temp;
    }
}
