package com.swordpointing2offer.lcr049;

class Solution {
    // 特殊处理根节点
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 处理根节点是叶子节点的情况
        if (root.left == null && root.right == null) {
            return root.val;
        }
        // 处理根节点的子节点
        int sum = 0;
        if (root.left != null) {
            sum += sumNumbers(root.left, root.val);
        }
        if (root.right != null) {
            sum += sumNumbers(root.right, root.val);
        }
        return sum;
    }

    // 处理子节点，把上层的值传递到下层
    public int sumNumbers(TreeNode root, int curNum) {
        if (root == null) {
            return 0;
        }
        // 叶子节点
        curNum = curNum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return curNum;
        }
        // 非叶子节点
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
