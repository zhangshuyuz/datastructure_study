package com.yuu.day02;

import java.util.ArrayList;
import java.util.List;

/**
 * 长度为N的数组arr，一定可以组成N^2个数值对。例如arr=[3, 1, 2]，数值对为(3,3)(3,1)(3,2)(1,3)(1,1)(1,2)(2,3)(2,1)(2,2)，也就是任意两个树都有数值对，而且自己和自己也算数值对。数值对如何排序？
 * 规定，第一维数据从小到大，第二维数据也从小到大，因此上述的变为(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)。
 * 要求：给定一个数组arr和整数k，返回第k小的数值对
 */
public class Section2 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 3, 3, 3, 4, 4};
        List<Integer> integers = process2(arr, 5);
        System.out.println(integers.toString());
    }

    /**
     * 排序arr后求数值对
     * @param arr
     * @param k
     * @return
     */
    public static List<Integer> process1(int[] arr, int k) {

        if (k > arr.length * arr.length) {
            return null;
        }

        // 排序arr。使用快速排序
        quick(arr, 0, arr.length - 1);

        // 获取第一维索引
        int firstNum = arr[ (k - 1) / arr.length ];

        // 比firstNum小的数
        int less = 0;
        // 和firstNum相等的数
        int equal = 0;
        for (int i = 0;  i < arr.length && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                less++;
            } else {
                equal++;
            }
        }

        // 第二维数获得
        int rest = k - (less * arr.length);
        int secondNum = arr[ (rest - 1) / equal ];

        List<Integer> result = new ArrayList<>();
        result.add(firstNum);
        result.add(secondNum);
        return result;
    }

    /**
     * 快速排序arr
     * @param arr
     * @return
     */
    public static void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int temp = 0;
        int pivot = arr[ left + (right - left) / 2];

        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }

            if (l >= r) {
                break;
            } else {
                temp = arr[r];
                arr[r] = arr[l];
                arr[l] = temp;
            }

            if (arr[l] == pivot) {
                l++;
            }
            if (arr[r] == right) {
                r--;
            }
        }

        if (l == r) {
            r--;
            l++;
        }

        if (left < r) {
            quick(arr, left, r);
        }

        if (right > l) {
            quick(arr, l, right);
        }

    }

    /**
     * 第二种方式
     * @param arr
     * @param k
     * @return
     */
    public static List<Integer> process2(int[] arr, int k) {

        if (k > arr.length * arr.length) {
            return null;
        }

        // 第一维数
        int firstNum = getNumFromArr(arr, (k - 1) / arr.length + 1);

        // 寻找第二维数
        // 比firstNum小的数
        int less = 0;
        // 和firstNum相等的数
        int equal = 0;
        for (int i = 0;  i < arr.length && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                less++;
            } else {
                equal++;
            }
        }

        // 第二维数获得
        int rest = k - (less * arr.length);
        int secondNum = getNumFromArr(arr, (rest - 1) / equal + 1);

        List<Integer> result = new ArrayList<>();
        result.add(firstNum);
        result.add(secondNum);
        return result;
    }

    /**
     * bfprt算法
     * @param arr
     * @param x
     * @return
     */
    public static int getNumFromArr(int[] arr, int x) {
        // 假设我们arr[0]是最小的，遍历后面的寻找第x小的就是找第arr.length - point小的
        int point = 0;
        int least = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (point == x) {
                return least;
            }

        }
    }

}
