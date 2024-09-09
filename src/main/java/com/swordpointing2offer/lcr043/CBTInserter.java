package com.swordpointing2offer.lcr043;

import java.util.ArrayDeque;
import java.util.Queue;

class CBTInserter {
    Queue<TreeNode> candidate;
    TreeNode root;

    public CBTInserter(TreeNode root) {
        this.candidate = new ArrayDeque<>();
        this.root = root;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        // 层序遍历，往列表中加入叶子节点其中一个为空的数据
        // 由于是从左往右层序遍历，并且满二叉树优先插入左边，所以只需要看头部节点即可
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (!(node.left != null && node.right != null)) {
                candidate.offer(node);
            }
        }
    }

    public int insert(int val) {
        TreeNode child = new TreeNode(val);
        TreeNode node = candidate.peek();
        int ret = node.val;
        if (node.left == null) {
            node.left = child;
        } else {
            node.right = child;
            candidate.poll();
        }
        // 新加入的节点，子节点必定为空，并且是最后插入的元素
        candidate.offer(child);
        return ret;
    }

    public TreeNode get_root() {
        return root;
    }
}

