package com.leetcode.q155;

import java.util.Deque;
import java.util.LinkedList;

class MinStack {
    Deque<Integer> stack = new LinkedList<>();
    Deque<Integer> minStack = new LinkedList<>();

    public MinStack() {
    }

    public void push(int val) {
        stack.addLast(val);
        Integer i = minStack.peekLast();
        if (i == null) {
            minStack.addLast(val);
            return;
        }
        minStack.addLast(Math.min(val, i));
    }

    public void pop() {
        stack.removeLast();
        minStack.removeLast();
    }

    public int top() {
        return stack.getLast();
    }

    public int getMin() {
        return minStack.getLast();
    }

    public static void main(String[] args) {
        MinStack minStack1 = new MinStack();
        minStack1.push(-1);
        minStack1.push(0);
        minStack1.push(-3);
        System.out.println(minStack1.getMin());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */