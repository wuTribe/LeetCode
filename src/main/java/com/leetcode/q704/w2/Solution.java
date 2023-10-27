package com.leetcode.q704.w2;

/**
 * 二分查找
 *
 * 左开右闭区间
 *
 * Created by wuyufan on 2023/10/27.
 */
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 闭区间，右侧无意义
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle;
            } else if (nums[middle] == target) {
                return middle;
            }
        }
        return -1;
    }
}
