package com.swordpointing2offer.lcr135;

import java.util.Arrays;

public class Solution {
    public int[] countNumbers(int cnt) {
        int length = 0;
        int num = 1;
        for (int i = 1; i <= cnt; i++) {
            length += num * 9;
            num *= 10;
        }
        int[] res = new int[length];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().countNumbers(2)));
    }
}
