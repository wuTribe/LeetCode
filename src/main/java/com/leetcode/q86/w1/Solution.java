package com.leetcode.q86.w1;

import com.wu.q86.base.ListNode;

public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode h1 = new ListNode(), curH1 = h1;
        ListNode h2 = new ListNode(), curH2 = h2;
        for (ListNode h = head; h != null; ) {
            ListNode t = h.next;
            h.next = null;
            if (h.val < x) {
                curH1.next = h;
                curH1 = curH1.next;
            } else {
                curH2.next = h;
                curH2 = curH2.next;
            }
            h = t;
        }

        curH1.next = h2.next;
        return h1.next;
    }
}
