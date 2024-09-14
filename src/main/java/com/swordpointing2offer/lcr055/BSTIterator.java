package com.swordpointing2offer.lcr055;

import java.util.Deque;
import java.util.LinkedList;

public class BSTIterator {
    private TreeNode cur;
    private Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new LinkedList<TreeNode>();
    }

    public int next() {
        // 左侧单调入栈，直到叶子节点
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        // 弹出叶子节点
        cur = stack.pop();
        int ret = cur.val;
        // 转到叶子节点的右节点
        cur = cur.right;
        return ret;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

}
