package com.swordpointing2offer.lcr146;

import java.util.Arrays;

public class Solution {
    public int[] spiralArray(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix[0].length * matrix.length];
        // t 上    r  右    b 下   l 左
        int l = 0, b = matrix.length - 1, r = matrix[0].length - 1, t = 0;
        int num = matrix[0].length * matrix.length;
        int k = 0;
        while (k < num) {
            // 上 l -> r
            for (int i = l; i <= r & k < num; i++) {
                res[k] = matrix[t][i];
                k++;
            }
            // 右 t -> b
            t++;
            for (int i = t; i <= b & k < num; i++) {
                res[k] = matrix[i][r];
                k++;
            }
            // 下 r -> l
            r--;
            for (int i = r; i >= l & k < num; i--) {
                res[k] = matrix[b][i];
                k++;
            }
            // 左 b -> t
            b--;
            for (int i = b; i >= t & k < num; i--) {
                res[k] = matrix[i][l];
                k++;
            }
            l++;
        }
        return res;
    }



    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().spiralArray(new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}})));
    }
}
