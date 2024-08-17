package com.swordpointing2offer.lcr190;

public class Solution2 {
    // 把 a+b 利用位运算，转化成另外的 a+b，直到 b = 0
    public int add(int a, int b) {
        if (b == 0) {
            return a;
        }

        // 转换成非进位和 + 进位
        return add(a ^ b, (a & b) << 1);
    }
}
