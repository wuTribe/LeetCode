package com.swordpointing2offer.lcr024;


import lombok.Getter;
import lombok.Setter;

// Definition for singly-linked list.
@Getter
@Setter
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
