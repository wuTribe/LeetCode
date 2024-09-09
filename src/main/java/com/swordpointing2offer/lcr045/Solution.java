package com.swordpointing2offer.lcr045;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int curItemNum = 1;
        int rs = 0;
        while (!queue.isEmpty()) {
            int nextItemNum = 0;
            for (int i = 0; i < curItemNum; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    rs = node.val;
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
        return rs;
    }
}
