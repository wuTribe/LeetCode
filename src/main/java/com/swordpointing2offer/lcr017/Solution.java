package com.swordpointing2offer.lcr017;

import java.util.*;

// https://leetcode.cn/problems/M1oyTv/
public class Solution {
    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        int tLen = t.length();
        // 统计所需要的字符串
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE;
        int ansL = -1;
        int ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            // 满足条件，滑动左指针
            while (check() && l <= r) {
                // 不断压缩左右长度
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    // 判断字符和数量是否都满足
    public boolean check() {
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
        // System.out.println(new Solution().minWindow("a", "a"));
        // System.out.println(new Solution().minWindow("ab", "b"));
        System.out.println(new Solution().minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
    }
}
