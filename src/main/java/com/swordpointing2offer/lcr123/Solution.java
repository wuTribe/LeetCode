package com.swordpointing2offer.lcr123;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public int[] reverseBookList(ListNode head) {
        Deque<Integer> deque = new LinkedList<>();
        int length = 0;
        while (head != null) {
            deque.addLast(head.val);
            head = head.next;
            length++;
        }
        int[] res = new int[deque.size()];
        for (int i = 0; i < length; i++) {
            res[i] = deque.removeLast();
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(6);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(1);
        System.out.println(Arrays.toString(new Solution().reverseBookList(head)));
    }
}

