package question_3;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
public class Test {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("uqinntq"));
    }
}

// 滑动窗口
class Solution {
    public int lengthOfLongestSubstring(String s) {
        String[] split = s.split("");
        if ("".equals(split[0])) return 0;
        int max = 0;

        int start = 0;
        int end = 0;

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            if (map.containsKey(split[i])) {
                // 冲突
                Integer index = map.get(split[i]);

                max = Math.max(max, (end - start));
                for (int j = start; j <= index; j++) {
                    map.remove(split[j]);
                }

                start = index + 1;
            }

            map.put(split[i], i);
            end++;
        }
        return Math.max(max, (end - start));
    }
}
