package com.leetcode.q438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> rs = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return rs;
        }

        int pLength = p.length();
        int[] ss = new int[26];
        int[] pp = new int[26];
        for (int i = 0; i < pLength; i++) {
            ss[s.charAt(i) - 'a']++;
            pp[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(ss, pp)) {
            rs.add(0);
        }
        for (int i = pLength; i < s.length(); i++) {
            ss[s.charAt(i) - 'a']++;
            ss[s.charAt(i - pLength) - 'a']--;
            if (Arrays.equals(ss, pp)) {
                rs.add(i - pLength + 1);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findAnagrams("cbaebabacd", "abc"));
    }
}
