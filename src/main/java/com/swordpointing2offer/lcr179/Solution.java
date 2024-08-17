package com.swordpointing2offer.lcr179;

public class Solution {

    // 超时
    public int[] twoSum(int[] price, int target) {
        int[] result = new int[2];
        for (int i = 0; i < price.length; i++) {
            for (int j = i + 1; j < price.length; j++) {
                if (price[i] + price[j] == target) {
                    result[0] = price[i];
                    result[1] = price[j];
                    return result;
                }
            }
        }
        return result;
    }
}
