package com.leetcode.q1122;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) {
            list.add(num);
        }
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        list.sort((o1, o2) -> {
            if (map.containsKey(o1) || map.containsKey(o2)) {
                return map.getOrDefault(o1, 1001) - map.getOrDefault(o2, 1001);
            }
            return o1 - o2;
        });
        for (int i = 0; i < list.size(); i++) {
            arr1[i] = list.get(i);
        }
        return arr1;
    }
}
