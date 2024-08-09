package com.leetcode.q3132;

import java.util.Arrays;

class Solution {
    public static int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // 外层循环求差值
        for (int i = 2; i >= 0; i--) {
            int diff = nums2[0] - nums1[i];
            // 内层循环双指针匹配序列
            int k = 0;
            for (int j = i; j < nums1.length; j++) {
                if (nums1[j] + diff == nums2[k]) {
                    k++;
                    if (k == nums2.length) {
                        return diff;
                    }
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(minimumAddedInteger(
                new int[]{9, 4, 3, 9, 4},
                new int[]{7, 8, 8}
        ));
    }
}
