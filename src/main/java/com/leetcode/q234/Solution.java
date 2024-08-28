package com.leetcode.q234;

import java.util.Stack;

public class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        while (head != null) {
            if (!stack.isEmpty() && stack.peek() == head.val) {
                stack.pop();
            }
            head = head.next;
        }
        return stack.isEmpty();
    }
}
