package com.swordpointing2offer.lcr139;

import java.util.Arrays;

public class Solution {
    public int[] trainingPlan(int[] actions) {
        int left = 0;
        int right = actions.length - 1;
        while (left < right) {
            while (left < right && actions[left] % 2 == 1) {
                left++;
            }
            while (left < right && actions[right] % 2 == 0) {
                right--;
            }
            int temp = actions[left];
            actions[left] = actions[right];
            actions[right] = temp;
        }
        return actions;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().trainingPlan(new int[]{1, 2, 3, 4, 5})));
    }
}
