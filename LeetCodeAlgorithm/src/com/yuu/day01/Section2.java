package com.yuu.day01;

/**
 * 给定长度为m的字符串aim，以及一个长度为n的字符串str，
 * 问：能否在str中找到一个长度为m的连续子串，使得这个子串刚好由aim的m个字符组成，顺序无所谓，返回任意一个满足条件的一个子串的起始位置，未找到返回-1.
 */
public class Section2 {

    public static void main(String[] args) {
        String a = "acbbcbaac";
        String b = "acbbcbaac";
        System.out.println(process3(a, b));
    }

    /**
     * 第三种最好的方法
     */
    public static int process3(String aim, String str) {

        // 判断长度
        if (aim.length() > str.length()) {
            return -1;
        }

        // 将两个String转成char[]
        char[] aimArr = aim.toCharArray();
        char[] strArr = str.toCharArray();

        // 建立还款表，即统计aim中每个字符的频率
        int[] pay = new int[256];
        for (int i = 0; i < aim.length(); i++) {
            pay[ aimArr[i] ]++;
        }

        // 建立无效还款数量inValidTimes
        int inValidTimes = 0;

        // 初始化指针和队列长度
        int m = 0;
        int n = aim.length();

        // 找到str的第一个n长度的子串
        while (m < n) {
            if (pay[ strArr[m] ]-- == 0) {
                // 无效还款++
                inValidTimes++;
            }

            m++;
        }

        // 继续遍历str
        while (m < str.length()) {

            if (inValidTimes == 0) {
                // 还款完毕
                return m - n;
            }

            if (pay[ strArr[m] ]-- <= 0) {
                // 无效还款++
                inValidTimes++;
            }

            if (pay[ strArr[m - n] ]++ < 0) {
                // 本来就不需要，拿回去相当于有效拿钱，无效还款--】
                inValidTimes--;
            }

            m++;
        }

        // str中最后一个为n的子串判断，还款完毕还是无法还款
        // 这个刚好还可以解决str.length() == aim.length()的问题
        return inValidTimes == 0 ? m - n : -1;

    }


}
