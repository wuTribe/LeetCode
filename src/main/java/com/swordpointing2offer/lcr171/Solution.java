package com.swordpointing2offer.lcr171;

// https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/description/
public class Solution {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ap = headA;
        ListNode bp = headB;

        while (ap != bp) {
            ap = ap != null ? ap.next : headB;
            bp = bp != null ? bp.next : headA;
        }
        return ap;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);

        ListNode listNode2 = new ListNode(4);
        listNode2.next = new ListNode(5);
        listNode2.next.next = listNode.next;
        System.out.println(new Solution().getIntersectionNode(listNode, listNode2));
    }
}
