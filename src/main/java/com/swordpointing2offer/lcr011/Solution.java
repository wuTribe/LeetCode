package com.swordpointing2offer.lcr011;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/A1NYOS/submissions/557015650/
public class Solution {

    // 前缀和，把 0 转化成 -1
    // 累加的过程中，如果当前累积的 num，在前面已经出现过，说明从前面的位置到当前的位置之间的距离刚好可以抵消
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().findMaxLength(new int[]{0, 0, 0, 1, 0, 1, 1, 1, 1}));
    }
}
