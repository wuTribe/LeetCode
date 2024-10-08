package com.leetcode.q21;

public class Solution {
    public static void main(String[] args) {

        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode listNode = mergeTwoLists(list1, list2);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode cur = head;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        if (list1 == null) {
            cur.next = list2;
        }
        if (list2 == null) {
            cur.next = list1;
        }

        return head.next;
    }
}
