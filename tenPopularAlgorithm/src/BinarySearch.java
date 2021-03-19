public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 7, 11, 25, 26, 37, 38, 49, 52};
        int target = 960;
        int index = binarySearchNotRecursion(arr, target);
        if (index != -1) {
            System.out.printf("%d是第%d个元素", target, index + 1);
        } else {
            System.out.printf("没有找到%d元素", target);
        }
    }

    /**
     * 递归方式实现二分查找
     * @param left 左指针
     * @param right 右指针
     * @param arr 待查找数组
     * @param target 目标元素
     * @return 目标元素下标
     */
    public static int binarySearchByRecursion(int left, int right, int[] arr, int target) {

        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + ((target - arr[left]) / (arr[right] - arr[left])) * (right - left);
        if (arr[mid] > target) {
            return binarySearchByRecursion(left, mid - 1, arr, target);
        }
        if (arr[mid] < target) {
            return binarySearchByRecursion(mid + 1, right, arr, target);
        }
        return mid;
    }

    public static int binarySearchByRecursion(int[] arr, int target) {
        return binarySearchByRecursion(0, arr.length - 1, arr, target);
    }

    /**
     * 非递归实现二分查找
     * @param arr 待查询数组
     * @param target 查询的元素
     * @return 查询的元素在数组的下标
     */
    public static int binarySearchNotRecursion(int[] arr, int target) {
        int left = 0;// 左指针
        int right = arr.length - 1;// 右指针
        int mid = 0; // 存储中间元素的下标

        while (true) {
            if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
                // 数组中没有该元素，返回-1
                return -1;
            }
            mid = (left + right) / 2;
            if (arr[mid] == target) {
                // 找到元素对应的下标，退出迭代
                break;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        // 将元素对应下标返回
        return mid;
    }
}

