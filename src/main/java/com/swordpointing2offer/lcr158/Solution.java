package com.swordpointing2offer.lcr158;

import java.util.Arrays;
import java.util.Objects;

public class Solution {
    public int inventoryManagement(int[] stock) {
        Arrays.sort(stock);
        int midCount = stock.length >> 1;
        int curCount = 1;
        Integer curNum = null;
        for (int i1 : stock) {
            if (curNum == null) {
                curNum = i1;
                continue;
            }
            // 如果还在遍历一样的数，计数++
            if (Objects.equals(curNum, i1)) {
                curCount++;
                continue;
            }
            // 否则，判断是否大于中间数
            if (curCount > midCount) {
                return curNum;
            }
            // 清空
            curNum = i1;
            curCount = 1;
        }
        return curNum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().inventoryManagement(new int[]{3,2,3}));
    }
}
