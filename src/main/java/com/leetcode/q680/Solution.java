package com.leetcode.q680;

public class Solution {

    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, true);
    }


    public boolean validPalindrome(String s, int left, int right, boolean reverse) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (reverse) {
                    return validPalindrome(s, left + 1, right, false)
                            || validPalindrome(s, left, right - 1, false);
                }
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
