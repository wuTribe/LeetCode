package question_1;

import java.util.Arrays;

// https://leetcode-cn.com/problems/two-sum/
public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}

/**
 * 暴力破解法
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] rs = null;
        boolean flag = true;
        for (int i = 0; (i < nums.length - 1) && flag; i++) {
            for (int j = i + 1; (j < nums.length) && flag; j++) {
                if (nums[i] + nums[j] == target) {
                    rs = new int[]{i, j};
                    flag = false;
                }
            }
        }

        return rs;
    }
}
