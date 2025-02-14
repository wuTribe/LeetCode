package com.swordpointing2offer.lcr063;

import java.util.List;

public class Solution {
    // 前缀树数据结构是一个字符多叉树，用一个数组来保存其子节点， isValid用来标记截止到该节点是否为一个完整的单词
    static class TrieNode {
        TrieNode[] child;
        boolean end;

        public TrieNode() {
            child = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();

    public String replaceWords(List<String> dictionary, String sentence) {
        String[] words = new String[dictionary.size()];
        for (int i = 0; i < words.length; ++i) {
            words[i] = dictionary.get(i);
        }
        // 建树过程
        for (String word : words) {
            insert(root, word);
        }
        String[] strs = sentence.split(" ");
        for (int i = 0; i < strs.length; ++i) {
            // 如果可以在树中找到对应单词的前缀，那么将这个单词替换为它的前缀
            if (search(root, strs[i])) {
                strs[i] = replace(strs[i], root);
            }
        }
        // 用StringBuilder来把字符串数组还原成原字符串句子的转换目标字符串
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 建前缀树模版
    public void insert(TrieNode root, String s) {
        TrieNode node = root;
        for (char ch : s.toCharArray()) {
            if (node.child[ch - 'a'] == null) node.child[ch - 'a'] = new TrieNode();
            node = node.child[ch - 'a'];
        }
        node.end = true;
    }

    // 查询是否存在传入的字符串的前缀
    public boolean search(TrieNode root, String s) {
        TrieNode node = root;
        for (char ch : s.toCharArray()) {
            if (node.end) {
                break;
            }
            if (node.child[ch - 'a'] == null) {
                return false;
            }
            node = node.child[ch - 'a'];
        }
        return true;
    }

    // 将传入的字符串替换为它在前缀树中的前缀字符串
    public String replace(String s, TrieNode root) {
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (node.end || node.child[ch - 'a'] == null) {
                break;
            }
            node = node.child[ch - 'a'];
            sb.append(ch);
        }
        return sb.toString();
    }
}
