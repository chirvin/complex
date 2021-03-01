package com.test.string;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Demo3 {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome2(s));
    }

    public static boolean isPalindrome2(String content) {
        int left = 0;
        int right = content.length() - 1;
        while (left < right) {
            // 数据预处理
            while (left < right && !Character.isLetterOrDigit(content.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(content.charAt(right))) {
                right--;
            }
            if (left < right) {
                if (Character.toLowerCase(content.charAt(left)) != Character.toLowerCase(content.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

}
