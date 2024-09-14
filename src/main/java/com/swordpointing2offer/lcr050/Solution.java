package com.swordpointing2offer.lcr050;

import java.util.HashMap;
import java.util.Map;

class Solution {
    // 记录当前key（前缀和）出现value次数
    private Map<Long, Integer> preSumCount = new HashMap<>();
    // 目标值
    private int target;

    public int pathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        // 记录前缀和为0的次数为1
        preSumCount.put(0L, 1);
        // 前缀和使用Long，原因单节点最大值10的9次方，使用int会溢出
        return dfs(root, 0L);
    }

    // 递归
    private int dfs(TreeNode node, Long currSum){
        // base case
        if (node == null) return 0;

        // 记录本层节点及以下节点中出现target的次数
        int res = 0;
        currSum += node.val;

        // 当前前缀和减去目标值 如果Map中有记录，则说明从某一点到当前节点的和等于目标值target
        res += preSumCount.getOrDefault(currSum - target, 0);

        // 把当前前缀和加入Map
        preSumCount.put(currSum, preSumCount.getOrDefault(currSum, 0) + 1);

        // 递归左右子树
        res += dfs(node.left, currSum);
        res += dfs(node.right, currSum);

        // 恢复本层记录过的前缀和,恢复状态的原因是因为防止在不同分支上的节点满足target，但并不属于题目要求
        preSumCount.put(currSum, preSumCount.get(currSum) - 1);

        return res;
    }
}
