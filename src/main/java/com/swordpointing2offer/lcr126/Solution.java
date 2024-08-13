package com.swordpointing2offer.lcr126;

// 递归超时
public class Solution {
    int[] arr = new int[101];
    {
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }
    }
    public int fib(int n) {
        return arr[n];
    }
}