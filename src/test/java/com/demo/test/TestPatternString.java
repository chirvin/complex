package com.demo.test;

import org.junit.Test;

/**
 * @author scc
 * @date 2020/7/13 14:40
 */
public class TestPatternString {

    @Test
    public void testPatternString() {
        // KMP算法、头部比较
        // 前缀、后缀、通过共有元素、组建部分匹配表
        // 移动位数 = 已匹配的字符数 - 对应的部分匹配值
        // 时间复杂度：O(M+N)
        // 空间复杂度：O(M)、M是需要匹配的字符串的长度

        // Demo：
        // 原字符串："BBC ABCDAB ABCDABCDABDE"
        // 需要匹配的字符串："ABCDABD"
        // A的前缀：无，后缀，无，共有长度为0
        // AB的前缀：A，后缀，B，共有长度为0
        // ABC的前缀：A、AB，后缀：BC、C，共有长度为0
        // ABCD的前缀：A、AB、ABC，后缀：BCD、CD、D，共有长度为0
        // ABCDA的前缀：A、AB、ABC、ABCD，后缀：BCDA、CDA、DA、A，共有长度为1
        // ABCDAB的前缀：A、AB、ABC、ABCD、ABCDA，后缀：BCDAB、CDAB、DAB、AB、B，共有长度为2
        // ABCDABD的前缀：A、AB、ABC、ABCD、ABCDA、ABCDAB，后缀：BCDABD、CDABD、DADB、ABD、BD、D，共有长度为0
        // 即匹配字符串的部分匹配表如下：
        // ABCDABD
        // 0000120
        String str = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        int index = kmp(str, pattern);
        System.out.println("首次出现位置：" + index);


        // Boyer-Moore算法（最优）、尾部比较
        // 坏字符：后移位数 = 坏字符的位置 - 搜索词中的上一次出现位置
        // 好后缀：后移位数 = 好后缀的位置 - 搜索词中的上一次出现位置
        // Boyer-Moore算法的基本思想是，每次后移这两个规则之中的较大值
        // 原字符串："HERE IS A SIMPLE EXAMPLE"
        // 需要匹配的字符串："EXAMPLE"

    }

    // 阮一峰提供的解法


    // 小灰提供的解法
    public static int kmp(String str, String pattern) {
        //预处理，生成next数组
        int[] next = getNexts(pattern);
        int j = 0;
        //主循环，遍历主串字符
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = next[j];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                //匹配成功，返回下标
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

    private static int[] getNexts(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;
        for (int i = 2; i < pattern.length(); i++) {
            while (j != 0 && pattern.charAt(j) != pattern.charAt(i - 1)) {
                //从next[i+1]的求解回溯到 next[j]
                j = next[j];
            }
            if (pattern.charAt(j) == pattern.charAt(i - 1)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
