package com.swordpointing2offer.lcr003;

import java.util.Arrays;

// https://leetcode.cn/problems/w3tCBm/solutions/1036712/qian-n-ge-shu-zi-er-jin-zhi-zhong-1-de-g-fkjq/
public class Solution2 {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().countBits(5)));
    }
}
