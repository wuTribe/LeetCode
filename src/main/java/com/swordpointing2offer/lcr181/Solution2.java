package com.swordpointing2offer.lcr181;

public class Solution2 {

    public String reverseMessage(String message) {
        // 删除首尾空格
        message = message.trim();
        int j = message.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            // 搜索首个空格
            while (i >= 0 && message.charAt(i) != ' ') {
                i--;
            }
            // 添加单词
            res.append(message, i + 1, j + 1).append(" ");
            // 跳过单词间空格
            while (i >= 0 && message.charAt(i) == ' ') {
                i--;
            }
            // j 指向下个单词的尾字符
            j = i;
        }
        // 转化为字符串并返回
        return res.toString().trim();
    }

    public static void main(String[] args) {
        // System.out.println(new Solution2().reverseMessage("a Hello World!!!!!"));
        System.out.println(new Solution2().reverseMessage("a good   example"));
    }
}
