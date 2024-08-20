package com.swordpointing2offer.lcr009;

public class Solution {
    // https://leetcode.cn/problems/ZVAVXX/submissions/556710143/
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0, right = 0, sum = 1;
        int ans = 0;
        while (right < nums.length) {
            sum *= nums[right];
            while (sum >= k && left <= right) {
                sum /= nums[left];
                left++;
            }
            ans += (right - left + 1);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
    }

}
