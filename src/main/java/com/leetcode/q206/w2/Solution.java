package com.leetcode.q206.w2;


import com.leetcode.q206.base.ListNode;

public class Solution {

    /** 双指针 */
    public ListNode reverseList(ListNode head) {
        ListNode resHead = new ListNode(0);


        for (ListNode i = head; i != null; ) {
            ListNode newHead = i.next;

            i.next = resHead.next;
            resHead.next = i;

            i = newHead;
        }
        return resHead.next;
    }


}
