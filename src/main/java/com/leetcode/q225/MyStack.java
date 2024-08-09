package com.leetcode.q225;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
    List<Integer> list = new ArrayList<>();

    public MyStack() {

    }

    public void push(int x) {
        list.add(x);
    }

    public int pop() {
        return list.remove(list.size() - 1);
    }

    public int top() {
        return list.get(list.size() - 1);
    }

    public boolean empty() {
        return list.isEmpty();
    }
}
