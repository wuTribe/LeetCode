package com.leetcode.q154;

public class Solution {

    public int findMin(int[] nums) {

        int min = Integer.MAX_VALUE;
        for (int j : nums) {
            min = Math.min(min, j);
        }
        return min;
    }
}
