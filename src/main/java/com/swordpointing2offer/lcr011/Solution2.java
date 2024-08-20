package com.swordpointing2offer.lcr011;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/A1NYOS/submissions/557015650/
public class Solution2 {
    public int findMaxLength(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(counter, -1);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().findMaxLength(new int[]{0, 0, 0, 1, 0, 1, 1, 1, 1}));
    }
}
