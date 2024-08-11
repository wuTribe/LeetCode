package com.swordpointing2offer.lcr023;


import lombok.Getter;
import lombok.Setter;

// Definition for singly-linked list.
@Getter
@Setter
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
