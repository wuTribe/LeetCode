package com.swordpointing2offer.lcr173;

public class Solution2 {

    public int takeAttendance(int[] records) {
        int left = 0;
        int right = records.length - 1;
        while (left <= right) {
            int m = (left + right) / 2;
            if (records[m] == m) {
                left = m + 1;
            } else {
                right = m - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().takeAttendance(new int[]{1, 2, 3, 4, 5}));
    }
}
