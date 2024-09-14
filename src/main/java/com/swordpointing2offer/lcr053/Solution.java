package com.swordpointing2offer.lcr053;

public class Solution {
    TreeNode res;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        inorderSuccessor(root.left, p);
        if (res == null && root.val > p.val) {
            res = root;
            return res;
        }
        inorderSuccessor(root.right, p);
        return res;
    }

}
