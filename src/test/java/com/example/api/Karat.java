package com.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@SpringBootTest
class Karat {
    //-1+7-324+32
    @Test
    void cal1() {
        String s = "-1+7";
        Integer res = 0;
        Integer sign = 1;
        Integer num = 0;
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-') {
                num = sign * num;
                res += num;
                sign = ch == '+' ? 1 : -1;
                num = 0;
            }
            if (Character.isDigit(ch)) {
                num = 10 * num + (ch - '0');
            }
        }
        res += sign * num;
        System.out.println(res);
    }

    //(1+23)-23+(34+1)-3
    @Test
    void cal() {
        String s = "(1+ ( 4+5 +2)- 3 )+ (6+8)";
        Stack<Integer> stack = new Stack<>();
        Integer res = 0;
        Integer sign = 1;
        Integer num = 0;
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-') {
                res += sign * num;
                sign = ch == '+' ? 1 : -1;
                num = 0;
            } else if (Character.isDigit(ch)) {
                num = 10 * num + (ch - '0');
            } else if (ch == '(') {
                stack.push(res);
                stack.push(sign);
                sign = 1; res = 0;
            } else if (ch == ')') {
                res += sign * num;
                res = res * stack.pop();
                res += stack.pop();
                num = 0;
            }
        }
        res += sign * num;
        System.out.println(res);
    }

    //x+a+b+f+c+1+d",{'a':1, 'b':2, 'c':3}
    @Test
    void cal3() {
        String s = "x+a+b+f+c+1+dfdgvv";
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put(null,1);

        Integer res = 0;
        Integer sign = 1;
        Integer num = 0;
        StringBuilder sb = new StringBuilder();
        char operation = '+';
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                Integer tmpNum = map.get(String.valueOf(ch));
                if (tmpNum != null) {num = tmpNum; }
                else { sb.append(operation).append(ch); }
            }
            if (Character.isDigit(ch)) {num = num * 10 + (ch - '0');}
            if (ch == '+' || ch == '-') {
                res += num * sign;
                sign = ch == '+' ? 1 : -1;
                num = 0; operation = ch;}
        }
        res += sign * num;
        System.out.println(String.valueOf(res) + sb.toString());
    }

    //1+2*3+5/2-4
    @Test
    void f() {
        String s = "1+2*3+5/2-4";

        Integer num = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                switch(sign) {
                    case '+':
                        stack.push(num); break;
                    case '-':
                        stack.push(-1 * num);break;
                    case '*':
                        stack.push(stack.pop() * num);break;
                    case '/':
                        stack.push(stack.pop() / num);break;
                }
                sign = ch; num = 0;
            }
        }
        Integer res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
//        return res;
        System.out.println(res);
    }


}
