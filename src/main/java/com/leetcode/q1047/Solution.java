package com.leetcode.q1047;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Character> stack = new ArrayList<>();

    public String removeDuplicates(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            if (stack.isEmpty()) {
                stack.add(c1);
                continue;
            }
            int lastIndex = stack.size() - 1;
            Character c = stack.get(lastIndex);
            if (c1 == c) {
                stack.remove(lastIndex);
                continue;
            }
            stack.add(c1);
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicates("azxxzy"));
    }
}
