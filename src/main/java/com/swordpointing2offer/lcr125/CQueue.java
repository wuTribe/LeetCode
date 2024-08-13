package com.swordpointing2offer.lcr125;

import java.util.ArrayList;
import java.util.List;

public class CQueue {
    List<Integer> in = new ArrayList<>();
    List<Integer> out = new ArrayList<>();

    public CQueue() {

    }

    public void appendTail(int value) {
        in.add(value);
    }

    public int deleteHead() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.add(in.remove(0));
            }
            if (out.isEmpty()) {
                return -1;
            }
        }
        return out.remove(0);
    }
}
