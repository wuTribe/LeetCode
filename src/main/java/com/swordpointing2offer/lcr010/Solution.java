package com.swordpointing2offer.lcr010;

public class Solution {

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // 负数导致的后果是，窗口增大的时候，和不一定增大，违背了滑窗的性质
    // public int subarraySum(int[] nums, int k) {
    //     int left = 0, right = 0, sum = 0;
    //     int ans = 0;
    //     while (right < nums.length) {
    //         sum += nums[right];
    //         while (left <= right && sum >= k) {
    //             if (sum == k) {
    //                 ans++;
    //             }
    //             sum -= nums[left];
    //             left++;
    //         }
    //         right++;
    //     }
    //     return ans == Integer.MAX_VALUE ? 0 : ans;
    // }

    public static void main(String[] args) {
        // System.out.println(new Solution().subarraySum(new int[]{1, 1, 1}, 2));
        // System.out.println(new Solution().subarraySum(new int[]{1, 2, 3}, 3));
        // System.out.println(new Solution().subarraySum(new int[]{1}, 0));
        System.out.println(new Solution().subarraySum(new int[]{-1, -1, 1}, 0));
    }
}
