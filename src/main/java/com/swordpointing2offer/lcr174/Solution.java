package com.swordpointing2offer.lcr174;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int findTargetNode(TreeNode root, int cnt) {
        if (root == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        findTargetNode(root.left, list);
        list.add(root.val);
        findTargetNode(root.right, list);
        return list.get(list.size() - cnt);
    }


    public void findTargetNode(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        findTargetNode(root.left, list);
        list.add(root.val);
        findTargetNode(root.right, list);
    }
}
