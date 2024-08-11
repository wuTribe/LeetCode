package com.swordpointing2offer.lcr006;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            Integer index = map.get(numbers[i]);
            if (index != null) {
                return new int[]{index, i};
            }
            map.put(target - numbers[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{1, 2, 4, 6, 10}, 8)));
    }
}
