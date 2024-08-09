package com.leetcode.q485;

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int rs = 0;
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            // 保存，清零
            if (i + 1 < nums.length && nums[i] != 1) {
                rs = Math.max(rs, tmp);
                tmp = 0;
            }
            if (nums[i] == 1) {
                tmp++;
            }
        }
        rs = Math.max(rs, tmp);
        return rs;
    }
}