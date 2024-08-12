package com.swordpointing2offer.lcr120;

// https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
// 原地交换
public class Solution2 {

    public int findRepeatDocument(int[] documents) {
        if (documents == null || documents.length == 0) {
            return 0;
        }
        int i = 0;
        while (i < documents.length) {
            int tmp = documents[i];
            if (tmp == i) {
                i++;
                continue;
            }
            if (tmp == documents[tmp]) {
                return tmp;
            }
            documents[i] = documents[tmp];
            documents[tmp] = tmp;
        }
        return i;
    }
}
