package com.swordpointing2offer.lcr101;

import java.util.Arrays;

public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        int targetSum = 0;
        for (int num : nums) {
            targetSum += num;
            if (targetSum == sum) {
                return true;
            }
        }
        return false;
    }
}
