public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[] {2, 10, 8, -5, -7, 9, 12};
        int[] temp = new int[arr.length];
        merge(arr, 0, arr.length - 1, temp);
        for (int i :
                arr) {
            System.out.printf("%d\t", i);
        }
    }

    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void merge(int[] arr, int left, int right, int[] temp) {

        //分
        if (left >= right) {
            return;
        } else {
            merge(arr, left, (left + right) / 2, temp);
            merge(arr, (left + right) / 2 + 1, right, temp);

            //治
            int mid = (left + right) / 2;
            int i = left;
            int j = mid + 1;
            int k = 0;
            // 将有序的两个序列的元素按顺序压入临时数组temp
            while (i <= mid && j <= right) {
                if (arr[i] <= arr[j]) {
                    temp[k] = arr[i];
                    k++;
                    i++;
                } else {
                    temp[k] = arr[j];
                    k++;
                    j++;
                }
            }
            // 将剩余数据全部压入temp
            while (i <= mid) {
                temp[k] = arr[i];
                i++;
                k++;
            }
            while (j <= right) {
                temp[k] = arr[j];
                j++;
                k++;
            }
            // 将temp拷贝回arr
            int l = 0;
            int t = left;
            while(t <= right) {
                arr[t] = temp[l];
                l++;
                t++;
            }
        }
    }
}
