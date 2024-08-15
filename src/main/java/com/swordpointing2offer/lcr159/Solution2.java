package com.swordpointing2offer.lcr159;

import java.util.Arrays;

public class Solution2 {
    public int[] inventoryManagement(int[] stock, int cnt) {
        int[] vec = new int[cnt];
        Arrays.sort(stock);
        for (int i = 0; i < cnt; ++i) {
            vec[i] = stock[i];
        }
        return vec;
    }
}
