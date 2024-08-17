package com.swordpointing2offer.lcr182;

public class Solution {
    public String dynamicPassword(String password, int target) {
        if (password == null || password.isEmpty()) {
            return "";
        }
        if (target == 0) {
            return password;
        }
        char[] charArray = new char[password.length()];
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (i + 1 <= target) {
                charArray[charArray.length - target + i] = c;
            } else {
                charArray[i - target] = c;
            }
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().dynamicPassword("s3cur1tyC0d3", 4));
    }
}
