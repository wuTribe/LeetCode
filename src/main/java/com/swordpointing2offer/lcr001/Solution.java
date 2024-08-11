package com.swordpointing2offer.lcr001;

// 麻烦点：
// 1. 边界条件多
// 2. 快速相减，位运算
public class Solution {
    public int divide(int a, int b) {
        // 特殊情况1, b=1，避免计算
        if (b == 1) {
            return a;
        }
        // 特殊情况2, b=-1，避免计算
        if (b == -1) {
            return a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
        }
        // 特殊情况3, a=0，不能计算
        if (a == 0) {
            return 0;
        }

        // 确定符号
        boolean positive = (a ^ b) >= 0;
        // 为避免溢出, 转换为负数进行计算
        a = a < 0 ? a : -a;
        b = b < 0 ? b : -b;
        // 快速相减，进行多次扣减
        int quotient = 0;
        while (a <= b) {
            int base = 1;
            int divisor = b;
            // 算出最大倍率，指数增长，一次性扣减
            while (a - divisor <= divisor) {
                divisor <<= 1;
                base <<= 1;
            }
            quotient += base;
            a -= divisor;
        }
        return positive ? quotient : -quotient;
    }



    public static void main(String[] args) {
        // 2 4 8 16 32
        System.out.println(new Solution().divide(20, 2));
    }
}
