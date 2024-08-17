package com.swordpointing2offer.lcr190;

public class Solution {
    // https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solutions/210882/mian-shi-ti-65-bu-yong-jia-jian-cheng-chu-zuo-ji-7/
    public int encryptionCalculate(int dataA, int dataB) {
        while (dataB != 0) { // 当进位为 0 时跳出
            int c = (dataA & dataB) << 1;  // c = 进位
            dataA ^= dataB; // dataA = 非进位和
            dataB = c; // dataB = 进位
        }
        return dataA;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().encryptionCalculate(1, 2));
    }
}
