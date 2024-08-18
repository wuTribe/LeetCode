package com.leetcode.q137;

// https://leetcode.cn/problems/single-number-ii/solutions/8944/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891/
class Solution2 {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}