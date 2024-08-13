package com.swordpointing2offer.lcr127;

public class Solution {
    int[] arr = new int[101];
    {
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }
    }
    public int trainWays(int n) {
        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().trainWays(5));
    }
}