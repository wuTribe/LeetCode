package com.swordpointing2offer.lcr005;

public class Solution {
    public int maxProduct(String[] words) {
        int[] marks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                marks[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((marks[i] & marks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
