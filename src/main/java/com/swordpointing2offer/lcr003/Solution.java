package com.swordpointing2offer.lcr003;

import java.util.Arrays;

/**
 * 利用 Brian Kernighan 算法，可以在一定程度上进一步提升计算速度。
 * Brian Kernighan 算法的原理是：对于任意整数 x，令 x=x & (x−1)
 * 该运算将 x 的二进制表示的最后一个 1 变成 0。因此，对 x 重复该操作，直到 x 变成 0，则操作次数即为 x 的「一比特数」。
 * 对于 2 的幂直接归零，其他都是低位归零
 */
public class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = count(i);
        }
        return result;
    }

    private int count(int n) {
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().countBits(2)));
        int n = 0;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n - 1));
        System.out.println(Integer.toBinaryString(n & (n - 1)));
    }
}
