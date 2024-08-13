package com.swordpointing2offer.lcr136;

public class Solution {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            head = head.next;
            return head;
        }
        ListNode cur = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val == val) {
                cur.next = next.next;
                return head;
            }
            cur = cur.next;
            next = next.next;
        }
        return head;
    }
}
