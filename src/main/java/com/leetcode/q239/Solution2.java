package com.leetcode.q239;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution2 {


    public int[] maxSlidingWindow(int[] nums, int k) {
        int [] res = new int[nums.length - k + 1];
        int i = 1 - k;
        int j = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        while (j <= nums.length - 1) {
            // 移除超过生命周期的元素
            if (i > 0 && queue.getFirst() == nums[i - 1]) {
                queue.removeFirst();
            }
            // 调整队列单调性，弹出所有比新窗口大小更小的元素
            // 因为移除的元素生命周期比最新元素要小
            while (!queue.isEmpty() && queue.getLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            if (i >= 0) {
                res[i] = queue.getFirst();
            }
            j++;
            i++;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
