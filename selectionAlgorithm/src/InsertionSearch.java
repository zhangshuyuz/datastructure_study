import java.util.ArrayList;
import java.util.List;

public class InsertionSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        List<Integer> index = insert(arr, 0, arr.length - 1, 9);
        for (Integer i :
                index) {
            System.out.println("index=" + i);
        }
    }

    /**
     * 插值查找
     * @param arr
     * @param left
     * @param right
     * @param findValue
     */
    public static List insert(int[] arr, int left, int right, int findValue) {

        // 没有找到值，返回空集合
        // 为了防止mid越界，必须要加findValue < arr[0] || findValue > arr[arr.length - 1]的判断
        if (findValue < arr[0] || findValue > arr[arr.length - 1] || left > right) {
            return new ArrayList<Integer>();
        }

        int insert = left + ((findValue - arr[left]) / (arr[right] - arr[left])) * (right - left);
        int insertValue = arr[insert];

        if (findValue > insertValue) {
            return insert(arr, insert + 1, right, findValue);
        } else if (findValue < insertValue) {
            return insert(arr, left, insert - 1, findValue);
        } else {
            // 找到了值，就创建一个集合存放值得坐标;
            List<Integer> val = new ArrayList<>();
            val.add(insert);
            // 查找左边有无相同的值
            int l = insert - 1;
            while (true) {
                // 左边没有相同的值，或者左边没有值了退出
                if (l < 0 || arr[l] != findValue) {
                    break;
                }
                val.add(l);
                l = l - 1;
            }
            // 查找右边有无相同的值
            int r = insert + 1;
            while (true) {
                // 右边没有相同的值，或者右边没有值了退出
                if (r > arr.length - 1 || arr[r] != findValue) {
                    break;
                }
                val.add(r);
                r = r + 1;
            }
            return val;
        }
    }
}
