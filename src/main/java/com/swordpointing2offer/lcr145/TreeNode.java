package com.swordpointing2offer.lcr145;


import lombok.Getter;
import lombok.Setter;

// Definition for a binary tree node.
@Getter
@Setter
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

