package com.leetcode.q234;

public class Solution2 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        int rs = 0;
        int i = 0;
        while (head != null) {
            rs ^= head.val;
            head = head.next;
            i ++;
        }
        if (i == 1) {
            return true;
        }
        return rs == 0;
    }
}
