package com.leetcode.q220;

import java.util.TreeSet;

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 使用 TreeSet 来维护一个长度不超过 k 的滑动窗口
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cur = nums[i];
            // 寻找比cur小的最大数和比cur大的最小数，查找 l->cur 和 cur->r 范围
            // 使用 treeSet.floor(cur) 来查找比 cur 小的最大值（左边界 l）
            // 使用 treeSet.ceiling(cur) 查找比 cur 大的最小值（右边界 r）。
            Integer l = treeSet.floor(cur);
            Integer r = treeSet.ceiling(cur);
            if (l != null && (long) cur - l <= t) {
                return true;
            }
            if (r != null && (long) r - cur <= t) {
                return true;
            }
            treeSet.add(cur);
            // 维持一个大小为 k 的滑动窗口，移除最早的一个元素
            if (i >= k) {
                treeSet.remove(nums[i - k]);
            }
        }
        return false;
    }
}
