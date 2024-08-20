package com.swordpointing2offer.lcr007;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {
                break;
            }
            // 重复位置不计算
            if (i > 0 && num == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                // 重复位置不计算
                while (left > i + 1 && left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                if (left >= right) {
                    break;
                }
                int target = nums[left] + nums[right] + num;
                if (target == 0) {
                    res.add(Arrays.asList(num, nums[left], nums[right]));
                    left++;
                    right--;
                } else if (target > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().threeSum(new int[]{2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4}));
    }
}
