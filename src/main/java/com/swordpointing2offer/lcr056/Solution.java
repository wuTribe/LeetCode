package com.swordpointing2offer.lcr056;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        int val = root.val;
        if (set.contains(val)) {
            return true;
        }
        set.add(k - val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        System.out.println(new Solution().findTarget(root, 12));
    }
}
