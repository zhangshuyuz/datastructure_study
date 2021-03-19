import java.nio.charset.StandardCharsets;

public class BruteForce {
    public static void main(String[] args) {
        String str1 = "你好 你好你你好好你好你好好你好 再见再见吧再见见";
        String str2 = "再见见";
        int i = stringBruteForce(str1, str2);
        if (i != -1) {
            System.out.println(i);
        }
    }

    /**
     * 暴力匹配字符串
     * @param str1 目标字符串
     * @param str2 匹配字符串
     * @return
     */
    public static int stringBruteForce(String str1, String str2) {
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
                // 匹配失败，i向后移动一位，j重新置为0
                i = i - j + 1;
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

}
