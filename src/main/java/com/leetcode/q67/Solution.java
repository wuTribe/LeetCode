package com.leetcode.q67;

public class Solution {

    public String addBinary(String a, String b) {

        int flag = 0;
        int length = Math.max(a.length(), b.length());

        int aLen = a.length() - 1;
        int bLen = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (length > 0) {
            int as = aLen >= 0 ? a.charAt(aLen--) - '0' : 0;
            int bs = bLen >= 0 ? b.charAt(bLen--) - '0' : 0;
            sb.insert(0, (as + bs + flag) % 2);
            flag = (as + bs + flag) / 2;
            length--;
        }
        if (flag == 1) {
            sb.insert(0, flag);
        }
        return sb.toString();
    }
}
