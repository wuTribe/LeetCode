package com.swordpointing2offer.lcr174;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    int res = 0;
    int cut = 0;
    public int findTargetNode(TreeNode root, int cnt) {
        if (root == null) {
            return -1;
        }
        this.cut = cnt;
        findTargetNode(root);
        return res;
    }


    public void findTargetNode(TreeNode root) {
        if (root == null) {
            return ;
        }
        findTargetNode(root.right);
        cut--;
        if (cut == 0) {
            res = root.val;
            return;
        }
        findTargetNode(root.left);
    }


}
