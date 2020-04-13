package question_2;

// https://leetcode-cn.com/problems/add-two-numbers/
public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                solution.addTwoNumbers(
                        solution.createList(new int[]{2, 4, 3}),
                        solution.createList(new int[]{5, 6, 4, 1})));
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;

        // 临时指针
        ListNode tempL1 = l1;
        ListNode tempL2 = l2;
        boolean flag = false; //是否进位
        while (tempL1 != null || tempL2 != null) {
            int num = (tempL1 != null ? tempL1.val : 0) + (tempL2 != null ? tempL2.val : 0) + (flag ? 1 : 0);
            flag = num >= 10;

            temp.next = new ListNode(num % 10);
            temp = temp.next;

            // 移动指针
            tempL1 = tempL1 != null ? tempL1.next : null;
            tempL2 = tempL2 != null ? tempL2.next : null;
        }

        // 再进位
        if (flag) temp.next = new ListNode(1);

        return head.next;
    }

    /**
     * 创建不带头结点的链表
     * @param nums 数据
     * @return 链表
     */
    public ListNode createList(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode temp = head;
        for (int i = 1; i < nums.length; i++) {
            temp.next = new ListNode(nums[i]);
            temp = temp.next;
        }
        return head;
    }
}
