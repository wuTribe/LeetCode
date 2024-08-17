package com.swordpointing2offer.lcr186;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean checkDynasty(int[] places) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int place : places) {
            if (set.contains(place)) {
                return false;
            }
            if (place == 0) {
                continue;
            }
            set.add(place);
            min = Math.min(min, place);
            max = Math.max(max, place);
        }
        return max - min < 5;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkDynasty(new int[]{4, 7, 5, 9, 2}));
    }
}
