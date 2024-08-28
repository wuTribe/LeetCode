package com.swordpointing2offer.lcr030;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomizedSet {
    // 存储元素
    static int[] nums = new int[200010];
    // 随机使用
    Random random = new Random();
    // map - index 用于查找元素所在位置，用于快速删除，随机
    Map<Integer, Integer> map = new HashMap<>();
    // 用于交换删除元素
    int idx = -1;

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        nums[++idx] = val;
        map.put(val, idx);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int loc = map.remove(val);
        // 如果删除最后一个元素，不用处理 map
        if (loc != idx) {
            map.put(nums[idx], loc);
        }
        nums[loc] = nums[idx--];
        return true;
    }

    public int getRandom() {
        return nums[random.nextInt(idx + 1)];
    }
}
