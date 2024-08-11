package com.swordpointing2offer.lcr024;

public class Solution {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode a = null;
        ListNode b = head;
        // null -> head -> a -> b -> c
        while (b != null) {
            ListNode tmp = b.next;
            b.next = a;
            a = b;
            b = tmp;
        }
        return a;
    }
}
