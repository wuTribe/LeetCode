package com.swordpointing2offer.lcr180;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int[][] fileCombination(int target) {
        int left = 1;
        int right = 2;
        List<int[]> res = new ArrayList<>();
        while (left < right) {
            int length = right - left + 1;
            int sum = (left + right) * (length) / 2;
            if (sum == target) {
                int[] temp = new int[length];
                for (int i = 0; i < length; i++) {
                    temp[i] = i + left;
                }
                res.add(temp);
                right++;
            } else if (sum > target) {
                left++;
            } else {
                right++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().fileCombination(18)));
    }
}
