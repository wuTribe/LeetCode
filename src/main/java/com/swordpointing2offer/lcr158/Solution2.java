package com.swordpointing2offer.lcr158;

public class Solution2 {
    // https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/solutions/138691/mian-shi-ti-39-shu-zu-zhong-chu-xian-ci-shu-chao-3/
    // 投票选举
    public int inventoryManagement(int[] stock) {
        int x = 0;
        int votes = 0;
        for (int num : stock) {
            if (votes == 0) {
                x = num;
            }
            votes += ((num == x) ? 1 : -1);
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().inventoryManagement(new int[]{3, 2, 3}));
    }
}
