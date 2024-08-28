package com.swordpointing2offer.lcr025;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // 使用栈
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode newHead = new ListNode(-1);
        int add = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || add != 0) {
            int sum = add;
            sum += stack1.isEmpty() ? 0 : stack1.poll();
            sum += stack2.isEmpty() ? 0 : stack2.poll();
            add = sum / 10;
            ListNode cur = new ListNode(sum % 10);
            cur.next = newHead.next;
            newHead.next = cur;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);


        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        System.out.println(new Solution().addTwoNumbers(l1, l2));
    }
}
