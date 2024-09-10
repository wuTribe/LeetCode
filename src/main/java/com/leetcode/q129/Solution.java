package com.leetcode.q129;

class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int sum = 0;
        if (root.left != null) {
            sum += sumNumbers(root.left, root.val);
        }
        if (root.right != null) {
            sum += sumNumbers(root.right, root.val);
        }
        return sum;
    }


    public int sumNumbers(TreeNode root, int curNum) {
        if (root == null) {
            return 0;
        }
        curNum = curNum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return curNum;
        }
        int sum = 0;
        if (root.left != null) {
            sum += sumNumbers(root.left, curNum);
        }
        if (root.right != null) {
            sum += sumNumbers(root.right, curNum);
        }
        return sum;
    }
}
