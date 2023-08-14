package com.leetcode.q160.w1;


import com.leetcode.q160.base.ListNode;

import java.util.HashSet;

/**
 * 哈希表
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet set = new HashSet();
        for (ListNode head = headA; head != null; head = head.next) {
            set.add(head);
        }
        for (ListNode head = headB; head != null; head = head.next) {
            if (set.contains(head)) {
                return head;
            }
        }
        return null;
    }
}