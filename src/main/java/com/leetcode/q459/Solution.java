package com.leetcode.q459;

public class Solution {
    public static boolean repeatedSubstringPattern(String s) {
        String ss = s + s;
        ss = ss.substring(1, ss.length() - 1);
        return ss.contains(s);
    }

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
    }
}
