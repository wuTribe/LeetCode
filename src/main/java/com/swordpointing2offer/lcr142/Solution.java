package com.swordpointing2offer.lcr142;

public class Solution {

    public ListNode trainningPlan(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            }
            if (l1 != null && l1.val >= l2.val) {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }
        while (l1 != null) {
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
        }
        while (l2 != null) {
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(2);
        ListNode rs = new Solution().trainningPlan(l1, l2);
        while (rs != null) {
            System.out.println(rs.val);
            rs = rs.next;
        }
    }
}
