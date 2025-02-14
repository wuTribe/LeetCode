package com.swordpointing2offer.lcr076;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return -1;
        }
        return Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).skip(k - 1).findFirst().get();
    }
}
