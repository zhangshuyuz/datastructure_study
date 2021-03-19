/**
 * KMP算法
 * 字符串匹配算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "ABCABCDF";
        String str2 = "BCD";

        int index = kmp(str1, str2);
        System.out.println(index);
    }

    /**
     * kmp匹配
     * @param str1 目标字符串
     * @param str2 匹配字符串
     * @return
     */
    public static int kmp(String str1, String str2) {
        // 生成部分匹配表
        int[] kmpTable = createTable(str2);

        // 利用部分匹配表进行字符串匹配
        char[] strChar1 = str1.toCharArray();
        char[] strChar2 = str2.toCharArray();

        int i = 0; // 指向str1的指针
        int j = 0;// 指向str2的指针
        while (i < strChar1.length && j < strChar2.length) {
            if (strChar1[i] == strChar2[j]) {
                // 匹配成功
                i++;
                j++;
            } else {
                // 匹配失败
                if (j > 0) {
                    // 匹配的有字符
                    // i的移动位数=已经匹配的字符数-字符对应在部分匹配表的部分匹配值
                    i = i - j + (j - kmpTable[j - 1]);
                } else {
                    // 没有匹配到字符
                    i = i + 1;
                }
                j = 0;
            }
        }

        if (j == strChar2.length) {
            // str1匹配到了str2
            // 返回str1中匹配到str2首字母的下标
            i = i - j;
            return i;
        } else {
            return -1;
        }

    }

    /**
     * 根据传入的字符串生成该字符串的部分匹配表
     * @param str
     * @return
     */
    public static int[] createTable(String str) {
        // 将字符串转成char数组
        char[] strChar = str.toCharArray();
        // 初始化部分匹配表
        int[] table = new int[str.length()];
        table[0] = 0;// 首字符的部分匹配值一定是0

        // 生成字符串的部分匹配表
        int j = 0;
        for (int i = 1; i < strChar.length; i++) {
            // 为了阻止第一次j=0时就进入while循环，while判断先加入j>0判断
            while (j > 0 && strChar[i] != strChar[j]) {
                j = table[j - 1];
            }
            if (strChar[i] == strChar[j]) {
                j++;
            }
            table[i] = j;
        }

        // 返回部分匹配表
        return table;
    }
}
