package com.swordpointing2offer.lcr169;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    public char dismantlingAction(String arr) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : arr.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }
}
