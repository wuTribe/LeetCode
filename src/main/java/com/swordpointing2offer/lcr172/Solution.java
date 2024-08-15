package com.swordpointing2offer.lcr172;

public class Solution {

    public int countTarget(int[] scores, int target) {
        int count = 0;
        for (int score : scores) {
            if (score == target) {
                count++;
            }
        }
        return count;
    }
}
