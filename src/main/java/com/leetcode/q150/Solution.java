package com.leetcode.q150;

import java.util.*;

public class Solution {

    Deque<Integer> stack = new LinkedList<>();

    public int evalRPN(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
                continue;
            }
            // 操作符
            Integer a = stack.pop();
            Integer b = stack.pop();
            if (token.equals("+")) {
                stack.push(b + a);
            } else if (token.equals("-")) {
                stack.push(b - a);
            } else if (token.equals("*")) {
                stack.push(b * a);
            } else if (token.equals("/")) {
                stack.push(b / a);
            }
        }
        Integer peek = stack.peek();
        return peek == null ? 0 : peek;
    }

    private boolean isNumber(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}
