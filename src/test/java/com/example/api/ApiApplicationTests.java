package com.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@SpringBootTest
class ApiApplicationTests {

    @Test
    void contextLoads() {
        String[] tokens = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};

        Stack<Integer> stack = new Stack<>();
        Integer temp, a, b;
        for (int i = 0;i < tokens.length;i++) {
            String ch = tokens[i];
            switch (ch) {
                case "+":
                    temp = Integer.valueOf(stack.pop()) + Integer.valueOf(stack.pop());
                    stack.push(temp);
                    break;
                case "-":
                    b = Integer.valueOf(stack.pop());
                    a =  Integer.valueOf(stack.pop());
                    temp = a - b;
                    stack.push(temp);
                    break;
                case "*":
                    temp = Integer.valueOf(stack.pop()) * Integer.valueOf(stack.pop());
                    stack.push(temp);
                    break;
                case "/":
                    b = Integer.valueOf(stack.pop());
                    a =  Integer.valueOf(stack.pop());
                    temp = a / b;
                    stack.push(temp);
                    break;
                default:
                    stack.push(Integer.valueOf(ch));
            }
        }
        System.out.println(stack.peek());
    }

    @Test
    //组成一个数的方法数
    int f() {
        int amount = 3;
        int coin[] = new int[]{2};
        int dp[] = new int[amount+1];
        dp[0] = 1;
        for (int i = 0;i < coin.length;i ++) {
            for (int j = coin[i];j <= amount; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }
        return dp[amount];
    }

    @Test
    //使用最少的数组成一个数
    int f1() {
        int amount = 11;
        int[] coins = new int[]{1, 2, 5};
        int dp[] = new int[amount + 1];

        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = dp[i - coins[j]] + 1 > dp[i] ? dp[i] : dp[i - coins[j]] + 1;
                }
            }
        }
        if (amount + 1 == dp[amount]) {
            return -1;
        } else {
            return dp[amount];
        }
    }

    String getPalindrome (String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l --;r ++; }
        return s.substring(l + 1, r);
    }
    @Test
    void longestPalindrome() {
        String s = "sdferggwereree";
        String res = "";
        for (int i = 0;i < s.length();i ++) {
            String sub1 = getPalindrome(s, i, i);
            res = sub1.length() > res.length() ? sub1 : res;
            if ((i + 1) < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                String sub2 = getPalindrome(s, i, i + 1);
                res = sub2.length() > res.length() ? sub2 : res;
            }
        }
        System.out.println(res);
    }


    public static void ff(Integer[] a, int left, int right) {
        if (left >= right) { return; }
        int i = left, j = right;
        Integer indexNum = a[left];
        while (i < j) {
            int mid = (left + right) / 2;
            while (a[j] >= indexNum && j > i) { j --; }
            while (a[i] <= indexNum && i < j) { i ++; }
            Integer tmpNum = a[i]; a[i] = a[j];
            a[j] = tmpNum;
        }
        a[left] = a[i]; a[i] = indexNum;
        ff(a, left, j - 1);
        ff(a, j + 1, right);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 0, -4, 5, 7, 3, -9, 2};
        ff(a, 0, a.length - 1);
        System.out.println(a);
    }



}
