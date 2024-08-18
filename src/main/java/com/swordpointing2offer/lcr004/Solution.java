package com.swordpointing2offer.lcr004;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int value = map.getOrDefault(num, 0) + 1;
            if (value >= 3) {
                map.remove(num);
            } else {
                map.put(num, value);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }
}