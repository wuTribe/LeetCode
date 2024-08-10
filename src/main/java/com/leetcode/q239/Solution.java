package com.leetcode.q239;

import java.util.Arrays;

// 超时
class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k];
        int resIndex = 0;
        // 取窗口最大值
        int maxIndex = -1;
        int left = 0;
        int right = left + k;
        while (right < nums.length) {
            // 超出窗口，计算新的最大值
            if (maxIndex < left) {
                maxIndex = getMax(nums, left, right);
            }
            left++;
            right++;
            res[resIndex++] = nums[maxIndex];
        }
        return res;
    }

    private static int getMax(int[] nums, int left, int right) {
        int maxIndex = -1;
        for (int i = left; i < right; i++) {
            if (maxIndex < 0) {
                maxIndex = i;
            }
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
