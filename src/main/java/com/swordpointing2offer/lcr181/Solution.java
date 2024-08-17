package com.swordpointing2offer.lcr181;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public String reverseMessage(String message) {
        List<String> collect = Arrays.stream(message.split(" "))
                .filter(e -> e != null && !e.equals(" ") && !e.isEmpty())
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < collect.size(); i++) {
            sb.insert(0, collect.get(i));
            if (i != collect.size() - 1) {
                sb.insert(0, " ");
            }
        }
        return sb.toString();
    }
}
