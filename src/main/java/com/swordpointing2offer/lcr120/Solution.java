package com.swordpointing2offer.lcr120;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int findRepeatDocument(int[] documents) {
        if (documents == null || documents.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < documents.length; i++) {
            map.put(documents[i], map.getOrDefault(documents[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatDocument(new int[]{2, 2, 3, 3, 5}));
    }
}
