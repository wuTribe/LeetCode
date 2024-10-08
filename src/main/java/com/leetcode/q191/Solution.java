package com.leetcode.q191;

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            if (n != 1) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().hammingWeight(3));
    }
}
