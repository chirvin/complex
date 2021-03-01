package com.test.string;

/**
 * @author scc
 * @date 2020/8/11 9:18
 */
public class Demo6 {

    public static void main(String[] args) {
        String p = "abc";
        String s = "abcdefghijklmn";
        System.out.println(isSub(p, s));
    }

    public static boolean isSub(String p, String s) {
        char[] a1 = p.toCharArray();
        char[] a2 = s.toCharArray();
        int temp = 0;
        for (int i = 0; i < a1.length; i++) {
            boolean isHas = false;
            for (int j = temp; j < a2.length; j++) {
                if (a1[i] == a2[j]) {
                    if (i == p.length() - 1) {
                        return true;
                    }
                    temp = j;
                    isHas = true;
                    break;
                }
            }
            if (!isHas) {
                return false;
            }
        }
        return false;
    }
}
