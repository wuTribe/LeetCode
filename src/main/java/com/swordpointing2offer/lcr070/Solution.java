package com.swordpointing2offer.lcr070;

public class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int rs = 0;
        for (int num : nums) {
            rs ^= num;
        }
        return rs;
    }
}
