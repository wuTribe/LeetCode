package com.swordpointing2offer.lcr028;

public class Solution {
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    public Node dfs(Node node) {
        Node cur = node;
        // 记录链表的最后一个节点
        Node last = null;

        while (cur != null) {
            Node next = cur.next;
            //  如果有子节点，那么首先处理子节点
            if (cur.child != null) {
                Node childLast = dfs(cur.child);

                next = cur.next;
                //  将 node 与 child 相连
                cur.next = cur.child;
                cur.child.prev = cur;

                //  如果 next 不为空，就将 last 与 next 相连
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                // 将 child 置为空
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }

    public static void main(String[] args) {
        Node head = new Node();
        head.val = 1;
        head.next = new Node();
        head.next.val = 2;
        head.next.next = new Node();
        head.next.next.val = 3;

        head.next.next.child = new Node();
        head.next.next.child.val = 100;


        head.next.next.next = new Node();
        head.next.next.next.val = 4;
        head.next.next.next.next = new Node();
        head.next.next.next.next.val = 5;
        Node tmp = new Solution().flatten(head);
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }
}
