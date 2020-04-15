package question_13;

// https://leetcode-cn.com/problems/roman-to-integer/
public class Test {
    public static void main(String[] args) {
        System.out.println(new Solution().romanToInt("LVIII"));
    }
}

class Solution {
    public int romanToInt(String s) {
        char c; // 当前遍历到的字符
        int num = 0; // 记录总量

        int i = 0;
        while (i < s.length()) {
            c = s.charAt(i);
            if (isNext(c, s, i)) {
                num += changeNum(s.charAt(i + 1)) - changeNum(c);
                i ++;
            } else {
                num += changeNum(c);
            }

            i++;
        }

        return num;
    }

    private boolean isNext(char c, String s, int index) {
        switch (c) {
            case 'I':
                if (++index  < s.length()) {
                    switch (s.charAt(index)) {
                        case 'V':
                        case 'X':return true;
                    }
                }
            case 'X':
                if (++index  < s.length()) {
                    switch (s.charAt(index)) {
                        case 'L':
                        case 'C':return true;
                    }
                }
            case 'C':
                if (++index  < s.length()) {
                    switch (s.charAt(index)) {
                        case 'D':
                        case 'M':return true;
                    }
                }
            default:
                return false;
        }
    }

    private int changeNum(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}