package com.swordpointing2offer.lcr147;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
    Deque<Integer> in = new LinkedList<>();
    Deque<Integer> minStack = new LinkedList<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        in.addLast(x);
        Integer i = minStack.peekLast();
        if (i == null || x < i) {
            minStack.addLast(x);
        } else {
            minStack.addLast(i);
        }
    }

    public void pop() {
        in.removeLast();
        minStack.removeLast();
    }

    public int top() {
        return in.peekLast();
    }

    public int getMin() {
        return minStack.peekLast();
    }
}
