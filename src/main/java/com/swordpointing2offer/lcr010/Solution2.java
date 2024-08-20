package com.swordpointing2offer.lcr010;

import java.util.HashMap;

// https://leetcode.cn/problems/QTMn0o/solutions/959104/shua-chuan-jian-zhi-offer-day07-shu-zu-i-jdnu/
// https://leetcode.cn/problems/QTMn0o/solutions/1293139/qian-zhui-he-chai-zhi-de-zui-tong-su-jie-oq8q/
public class Solution2 {

    public int subarraySum(int[] nums, int k) {
        int pre_sum = 0;
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i : nums) {
            pre_sum += i;
            ret += map.getOrDefault(pre_sum - k, 0);
            map.put(pre_sum, map.getOrDefault(pre_sum, 0) + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7));
    }
}
