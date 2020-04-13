package question_3;

// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
public class Test2 {
    public static void main(String[] args) {
        System.out.println(new Solution2().lengthOfLongestSubstring("uqinntq"));
    }
}

// 尾部指针一直走，碰到相同的，头部指针直接跳到第一次出现的位置
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length < 1) return 0; // ""
        int maxLen = 1;
        for (int head = 0, tail = 1; tail < s.length(); tail++) {
            char c = s.charAt(tail); // 取出尾字符
            
            // 返回指定字符第一次出现的字符串中的索引，在指定的索引处开始搜索，找到对应下标
            int index = s.indexOf(c, head);
            if (index < tail) {
                head += (index - head + 1);
            }
            int len = tail - head + 1;
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }
}
