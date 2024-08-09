package com.leetcode.q232;

import java.util.ArrayList;
import java.util.List;

class MyQueue {

    List<Integer> in = new ArrayList<>();
    List<Integer> out = new ArrayList<>();

    public MyQueue() {
    }

    public void push(int x) {
        in.add(x);
    }

    public int pop() {
        if (out.isEmpty()) {
            addOut();
        }
        return out.remove(0);
    }

    public int peek() {
        if (out.isEmpty()) {
            addOut();
        }
        return out.get(0);
    }


    public boolean empty() {
        if (out.isEmpty()) {
            addOut();
        }
        return out.isEmpty();
    }

    private void addOut() {
        while (!in.isEmpty()) {
            out.add(in.remove(0));
        }
    }

    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        System.out.println(obj.empty());
    }
}



/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
