package com.swordpointing2offer.lcr173;

public class Solution {

    public int takeAttendance(int[] records) {
        for (int i = 0; i < records.length + 1; i++) {
            if (i >= records.length || records[i] != i) {
                return i;
            }
        }
        return -1;
    }
}
