package com.yuu.day02;

import java.util.Arrays;

/**
 * 输入一个int类型的值N，构造一个长度为N的数组arr并返回。
 * 要求：对于任意的i<k<j, 满足arr[i] + arr[j] != arr[k] * 2
 */
public class Section1 {
    public static void main(String[] args) {
        int[] process = process(500);
        System.out.println(isValid(process));
    }

    /**
     * 传入num，返回满足要求的数组
     * @param num
     * @return
     */
    public static int[] process(int num) {

        if (num == 1) {
            return new int[] { 1 };
        }

        // 初始化目标数组
        int[] target = new int[num];

        // 获取中间值。(num + 1) / 2，让num / 2做到了向上取整，保证2 * half > num即target数组一定能全部装填
        int half = (num + 1) / 2;

        // 获取长度为(num + 1) / 2的达标数组
        int[] base = process(half);

        // 奇变换
        for (int i = 0; i < half; i++) {
            target[i] = 2 * base[i] + 1;
        }

        // 偶变换
        int temp = 0;
        for (int j = half; j < num; j++) {
            target[j] = 2 * base[temp];
            temp++;
        }

        return target;
    }

    /**
     * 检验函数
     */
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
