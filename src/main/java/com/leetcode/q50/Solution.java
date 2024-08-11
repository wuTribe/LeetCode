package com.leetcode.q50;

public class Solution {
    public double myPow(double x, int n) {
        long tmpN = Math.abs((long) n);
        double res = 1.0;
        double pow = x;
        while (tmpN > 0) {
            // 最低位是 1，保存数值
            if ((tmpN & 1) == 1) {
                res *= pow;
            }
            pow *= pow;
            tmpN >>= 1;
        }
        return n > 0 ? res : 1 / res;
    }

    public static void main(String[] args) {
        // System.out.println(new Solution().myPow(3, 0));
        // System.out.println(Math.pow(3, 0));
        //
        // System.out.println(new Solution().myPow(2, -2));
        // System.out.println(Math.pow(2, -2));

        System.out.println(new Solution().myPow(2, -2147483648));
        System.out.println(Math.pow(2, -2147483648));
    }
}
