package com.leetcode.q206;


public class Solution2 {

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
