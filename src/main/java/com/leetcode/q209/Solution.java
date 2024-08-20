package com.leetcode.q209;

// https://leetcode.cn/problems/2VG8Kg/description/
public class Solution {

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, sum = 0;
        int ans = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
