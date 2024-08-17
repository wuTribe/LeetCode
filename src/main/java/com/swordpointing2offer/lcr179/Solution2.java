package com.swordpointing2offer.lcr179;

import java.util.HashSet;
import java.util.Set;

public class Solution2 {

    // 超时
    public int[] twoSum(int[] price, int target) {
        Set<Integer> set = new HashSet<>();
        for (int j : price) {
            int diff = target - j;
            if (set.contains(diff)) {
                return new int[]{diff, j};
            }
            set.add(j);
        }
        return new int[0];
    }
}
