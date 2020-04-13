package question_1;

import java.util.Arrays;
import java.util.HashMap;

// https://leetcode-cn.com/problems/two-sum/
public class Test1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution1().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}

/**
 * 利用哈希表
 * 求补数，将其放入哈希表中，接下来判断下一个数是不是补数即可
 */
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] rs = null;

        // 建立k-v ，一一对应的哈希表
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                rs = new int[]{hash.get(nums[i]), i};
                break;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target - nums[i], i);
        }

        return rs;
    }
}
