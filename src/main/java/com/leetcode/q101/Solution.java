package com.leetcode.q101;

public class Solution {

    public boolean isSymmetric(TreeNode root){
        if (root == null){
            return true;
        }
        return check(root.left, root.right);
    }
    public boolean check(TreeNode u, TreeNode v){
        if (u == null && v == null){
            return true;
        }
        if (u == null || v == null){
            return false;
        }
        if (u.val != v.val){
            return false;
        }
        return check(u.left, v.right) && check(u.right, v.left);
    }

}
