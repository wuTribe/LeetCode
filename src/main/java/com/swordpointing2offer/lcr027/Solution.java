package com.swordpointing2offer.lcr027;

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

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        System.out.println(new Solution().isPalindrome(head));
    }
}