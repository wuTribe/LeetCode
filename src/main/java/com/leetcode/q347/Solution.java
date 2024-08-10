package com.leetcode.q347;

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 默认最小堆，这里特殊指定算法
        // [0] 元素本身  [1] 出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer v = entry.getKey();
            Integer count = entry.getValue();
            // 构造优先队列
            if (queue.size() < k) {
                queue.offer(new int[]{v, count});
            } else {
                // 判断队列中最小的个数是否比当前元素还小
                // 如果是，抛弃队列的
                // 如果不是，抛弃当前元素
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{v, count});
                }
            }
        }
        int[] res = new int[k];
        // 这里会弹出元素，不能使用队列遍历
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}
