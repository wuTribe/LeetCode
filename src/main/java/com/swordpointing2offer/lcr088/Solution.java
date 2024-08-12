package com.swordpointing2offer.lcr088;

// 超时
public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        return minCostClimbingStairs(cost, -1);
    }


    public int minCostClimbingStairs(int[] cost, int start) {
        if (start >= cost.length) {
            return 0;
        }
        int cur = start == -1 ? 0 : cost[start];
        int one = minCostClimbingStairs(cost, start + 1);
        int two = minCostClimbingStairs(cost, start + 2);
        return Math.min((cur + one), (cur + two));
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
