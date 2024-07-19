package com.example.api;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Atlassian1 {

    //合并成一个数
    @Test
    void Q1() {
        Integer num = 1;
        while (num >= 10) {
            String numStr = String.valueOf(num);
            num = 0;
            for (int i = 0;i < numStr.length();i ++) {
                Integer tmpNum = numStr.charAt(i) - '0';
                num += tmpNum;
            }
        }
        System.out.println(num);
    }
    //二分排序+乘积最大
    public void Q2() {
        //List<Integer> list = Arrays.asList(1, 5, -17, -9, 23, 8);
        Integer[] a = new Integer[]{1, 5, -17, -9, 23, 8};
        int mid;
        int low = 0;
        int high = a.length - 1;
    }

    public static void quickSort(Integer[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        Integer indexNum = a[left];
        int i = left;
        int j = right;
        while (i < j) {
            //右边开始找第一个比indexNum 小的
            while (a[j] >= indexNum && i < j) {
                j --;
            }
            //左边开始找第一个比indexNum 大的
            while (a[i] <= indexNum && i < j) {
                i ++;
            }
            Integer tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
        }
        a[left] = a[i];
        a[i] = indexNum;
        quickSort(a, left, j - 1);
        quickSort(a, j + 1, right);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{-1, 9, 22, 3, -15, -7};
        quickSort(a, 0, a.length - 1);
        Integer res = Integer.MIN_VALUE;
        Integer tmp;
        if (a[0] < 0 && a[1] < 0) {
             tmp = a[0] * a[1] * a[a.length - 1];
             res = res < tmp ? tmp : res;
        }
        tmp = a[a.length - 1] *  a[a.length - 2] *  a[a.length - 3];
        res = res < tmp ? tmp : res;
        System.out.println(res);
    }

    @Test
    public void f() {
        String s = "3/2 +1/2 *4";
        char preSign = '+';
        Integer num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                switch (preSign) {
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
                preSign = ch;
                num = 0;
            }
        }

        Integer res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        System.out.println(res);
    }

    @Test
    public void Q4() {
        Integer a[] = new Integer[]{10,5,2,6};
        Integer k = 100;
        int i = 0, j = 0;
        Integer product = 1; Integer res = 0;
        for (j = 0;j < a.length;j ++) {
            product *= a[j];
            while (i < j && product >= k) {
                product /= a[i];
                i ++;
            }
            res += j - i + 1;}
        System.out.println(res);
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    @Test
    public void fanzhuan() {
        ListNode list = new ListNode();
        list = new ListNode(85);
        list.next = new ListNode(15);
        list.next.next = new ListNode(4);
        list.next.next.next = new ListNode(20);

        ListNode prev = null;
        ListNode curr = list;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        System.out.println(prev);
    }


}
