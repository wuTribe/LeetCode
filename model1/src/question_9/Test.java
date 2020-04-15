package question_9;

// https://leetcode-cn.com/problems/palindrome-number/
public class Test {
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(123321));
    }
}

class Solution {
    public boolean isPalindrome(int x) {
        // 如果为负数，肯定不是回文
        if (x < 0) return false;

        int num1 = x;
        int num2 = 0;

        while (x > 0) {
            num2 = num2 * 10 + x % 10;
            x /= 10;
        }

        return num1 == num2;
    }
}