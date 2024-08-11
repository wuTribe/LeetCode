package com.lcp.lcp66;

public class Solution {
    public int minNumBooths(String[] demand) {
        int[][] nums = new int[26][2];
        for (String s : demand) {
            for (int j = 0; j < s.length(); j++) {
                nums[s.charAt(j) - 'a'][0]++;
            }
            for (int[] num : nums) {
                if (num[0] > num[1]) {
                    num[1] = num[0];
                }
                num[0] = 0;
            }
        }
        int max = 0;
        for (int[] num : nums) {
            max += num[1];
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minNumBooths(new String[]{"acd", "bed", "accd"}));
    }
}
