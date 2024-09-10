package com.leetcode.q199;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curItemNum = 1;
        while (!queue.isEmpty()) {
            int nextItemNum = 0;
            for (int i = 0; i < curItemNum; i++) {
                TreeNode node = queue.poll();
                if (i == curItemNum - 1) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                    nextItemNum++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    nextItemNum++;
                }
            }
            curItemNum = nextItemNum;
        }
        return list;
    }
}
