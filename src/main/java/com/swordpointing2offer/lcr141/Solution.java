package com.swordpointing2offer.lcr141;

public class Solution {

    public ListNode trainningPlan(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = head;
        while (next != null) {
            ListNode tmp = next.next;
            next.next = pre;
            pre = next;
            next = tmp;
        }
        return pre;
    }
}
