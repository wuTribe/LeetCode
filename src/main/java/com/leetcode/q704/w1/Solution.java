package com.leetcode.q704.w1;

/**
 * 二分查找
 *
 * 左闭右闭区间
 *
 * Created by wuyufan on 2023/10/27.
 */
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 必须是有效区间
        while (left <= right) {  // [1, 1] 区间有意义
            int middle = left + ((right - left) >> 1); // 计算右侧的两个边界容易溢出
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
