package com.leetcode.q53;

public class Solution {

    public int maxSubArray(int[] sales) {
        int pre = 0;
        int max = pre;
        for (int sale : sales) {
            pre = Math.max(pre + sale, sale);
            max = Math.max(max, pre);
        }
        return pre;
    }
}
