package com.swordpointing2offer.lcr128;

public class Solution {
    public int stockManagement(int[] stock) {
        int min = Integer.MAX_VALUE;
        for (int j : stock) {
            min = Math.min(min, j);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().stockManagement(new int[]{4, 5, 8, 3, 4}));
    }
}
