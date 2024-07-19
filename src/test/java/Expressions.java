import org.junit.jupiter.api.Test;

import java.util.Stack;

public class Expressions {
    @Test
    public void f() {
        String s = "3*4 + 5 -2 /1 *8 +2";
        char preSign = '+';
        Stack<Integer> stack = new Stack<>();

        int num = 0;
        for (int i = 0;i < s.length();i ++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else {
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
                        stack.push(num / stack.pop());
                        break;
                }
                preSign = ch;
                num = 0;
            }
        }

    }
}
