public class RadixSort {
    public static void main(String[] args) {
        System.out.println(-99 % 10);
    }

    /**
     * 基数排序
     * @param arr
     */
    public static void radix(int[] arr) {

        // 找出最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大数的最大位数
        int maxLen = (max + "").length();
        // 针对每个数不同的位排序
        int k = 1;
        // 定义一个二维数组，用来存储arr中的元素
        // 为了防止数组溢出造成数组越界，我们定义每个一维数组（桶）的长度为arr.length
        int[][] temp = new int[10][arr.length];
        // 为了记录每个桶的实际数据，定义一个一维数组专门用来记录每个桶的有效数据个数，每个桶的初始实际数据都为0
        int[] bucketElementCounts = new int[10];

        for (int m = 0; m < maxLen; m++) {
            // 取出个位的数
            for (int i = 0; i < arr.length; i++) {
                // 取出arr每个数据的个位数

                int digitOfElement = arr[i] / k % 10;
                // 存放arr数据在桶中
                temp[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                // 让桶的实际数据+1
                bucketElementCounts[digitOfElement]++;
            }
            // 将temp中的每个数据依次回写给arr
            int index = 0; // arr的下标
            // 遍历每一个桶，桶中如果有数据回写给arr
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].length != 0) {
                    for (int j = 0; j < temp[i].length; j++) {
                        arr[index] = temp[i][j];
                        index++; // 每回写一条数据，arr索引向后移动
                    }
                    // 桶中数据回写完毕，清空桶，即桶中实际元素个数置为0
                    bucketElementCounts[i] = 0;
                }
            }
            // 每次循环，k = k * 10以便于依次获取每个数的不同位
            k = k * 10;
        }
    }
}
