package com.test.array;

/**
 * 数组中数字出现的次数
 * <p>
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Demo1 {

    public static void main(String[] args) {
        int[] a = {3, 2, 3, 6};
        int[] result = getOnceNumber(a);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] getOnceNumber(int[] num) {
        int[] result = new int[2];
        if (num == null || num.length == 0) {
            return result;
        }
        // 得到所有数值的异或结果，其实得到的是两个不同的数值的异或结果
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum ^= num[i];
        }
        // 获取两个数值不同的二进制为1的最低位
        int flag = sum & (-sum);
        // 分组
        for (int i = 0; i < num.length; i++) {
            if ((flag & num[i]) == 0) {
                result[0] ^= num[i];
            } else {
                result[1] ^= num[i];
            }
        }
        return result;
    }
}
