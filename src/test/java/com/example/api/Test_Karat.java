package com.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

@SpringBootTest
public class Test_Karat {
    @Test
    public void Q1() {
        //2+3-23
        String s = "0-3+1";
        Integer num = 0;
        Integer res = 0;
        int sign = 1;
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '-' || ch == '+') {
                res = num * sign + res;
                num = 0;
                sign = ch == '-' ? -1 : 1;
            }
        }
        res += sign * num;
        System.out.println(res);
    }

    @Test
    public void Q2() {
        //2+3-23
        String s = "2+((8+2)-(3-999))";
        Integer num = 0;
        Integer res = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '+' || ch == '-') {
                res += num * sign;
                num = 0;
                sign = ch == '+' ? 1 : -1;
            } else if (ch == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (ch == ')') {
                res += sign * num;

                int preSign = stack.pop();
                res *= preSign;

                Integer preRes = stack.pop();
                res += preRes;
                num = 0;
            }
        }

        res += num * sign;
        System.out.println(res);
    }


    @Test
    public void Q3() {
        //2+3-23
        String s = "a+b+c+1+d";
        Map<String, Integer> map = new HashMap();
        Integer num = 0;
        Integer res = 0;
        String op = "+";
        int sign = 1;
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '-' || ch == '+') {
                res = num * sign + res;
                num = 0;
                sign = ch == '-' ? -1 : 1;
            } else if (Character.isLetter(ch)) {
                Integer tmpNum = map.get(String.valueOf(ch));
                if (null == tmpNum) {
                    sb.append(op).append(ch);
                } else {
                    num = tmpNum;
                }
            }
        }
        res += sign * num;
        System.out.println(res + sb.toString());
    }

    @Test
    public void Q4() {
        Optional<String> b = Optional.of("afaf");
        String s = "2*3-1+  4 /   2";
        Integer num = 0;
        char op = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                switch (op) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-1 * num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                op = ch;
                num = 0;
            }
        }
        Integer res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        System.out.println(res);
    }
}
