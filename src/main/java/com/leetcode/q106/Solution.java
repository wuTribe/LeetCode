package com.leetcode.q106;

import java.util.Arrays;

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int lastIndex = postorder.length - 1;
        TreeNode root = new TreeNode(postorder[lastIndex]);
        int iIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                iIndex = i;
            }
        }
        // 处理左节点
        root.left = buildTree(Arrays.copyOfRange(inorder, 0, iIndex), Arrays.copyOfRange(postorder, 0, lastIndex));
        // 处理右节点
        root.right = buildTree(Arrays.copyOfRange(inorder, 0, iIndex), Arrays.copyOfRange(postorder, 0, lastIndex));

        return root;

    }

}
