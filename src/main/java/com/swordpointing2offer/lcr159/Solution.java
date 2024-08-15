package com.swordpointing2offer.lcr159;

import java.util.*;

public class Solution {
    public int[] inventoryManagement(int[] stock, int cnt) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : stock) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ) {
            int[] poll = queue.poll();
            if (poll != null) {
                for (int j = 0; j < poll[1] && i < cnt; j++) {
                    ints[i++] = poll[0];
                }
            }
        }
        return ints;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().inventoryManagement(new int[]{0, 0, 1, 3, 4, 5, 0, 7, 6, 7}, 9)));
    }
}
