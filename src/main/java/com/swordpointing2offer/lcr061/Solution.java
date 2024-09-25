package com.swordpointing2offer.lcr061;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
// num2 ---- 0 --- 1 --- 2 --- 3 --- 4
// num1 ---- 1
// num1 ---- 2
// num1 ---- 3
// num1 ---- 4
public class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 容量 k，排序规则是根据 nums1[i] + nums2[j] 的和进行排序，和小的优先
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        // 超出 k 会被淘汰,由于递增,后面必定比前 K 大
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            // 这里取到的一定是最小的
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            // 可能当前行后面的数据更小,往后挪动
            if (idxPair[1] + 1 < n) {
                // 排序
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }
}
