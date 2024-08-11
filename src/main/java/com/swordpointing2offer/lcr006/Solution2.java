package com.swordpointing2offer.lcr006;

import java.util.Arrays;
import java.util.HashMap;

public class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().twoSum(new int[]{1, 2, 4, 6, 10}, 8)));
    }
}
