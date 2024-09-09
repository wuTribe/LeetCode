package com.swordpointing2offer.lcr044;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    /**
     * 利用队列进行层序遍历
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curItemNum = 1;
        while (!queue.isEmpty()) {
            // 遍历一层
            Integer max = null;
            // 下一层数量
            int nextItemNum = 0;
            for (int i = 0; i < curItemNum; i++) {
                TreeNode node = queue.poll();
                if (max == null) {
                    max = node.val;
                } else {
                    max = Math.max(max, node.val);
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
            list.add(max);
            // 下一层数量
            curItemNum = nextItemNum;
        }
        return list;
    }
}
