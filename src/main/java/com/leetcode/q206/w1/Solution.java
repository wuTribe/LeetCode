package com.leetcode.q206.w1;


import com.wu.q206.base.ListNode;

class Solution {

    /** 递归 */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ret = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }
}
