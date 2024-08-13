package com.swordpointing2offer.lcr140;

public class Solution {
    // 快慢指针
    public ListNode trainingPlan(ListNode head, int cnt) {
        if (cnt == 0 || head == null) {
            return head;
        }
        ListNode fast = head;
        for (int i = 0; i < cnt && fast != null; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head;
        }
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
