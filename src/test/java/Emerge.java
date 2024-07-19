import org.junit.jupiter.api.Test;

import java.util.*;

public class Emerge {
    @Test
    public void f() {

        PriorityQueue q = new PriorityQueue();
        q.add("a");
        q.add("b");
        q.add("c");
        q.offer("c");
        System.out.print(q.size() + " " + q.poll());
        System.out.print(" " + q.peek() + " " + q.poll());
        System.out.print(" " + q.poll() + " " + q.poll());
    }

    @Test
    public void f1() {
        String s = "asaaa";
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;

        for (int i = 0;i < s.length();i ++) {
            String ch = String.valueOf(s.charAt(i));
            Integer prePos = map.get(ch);
            if (prePos != null) {
                left = Math.max(left, prePos + 1);
            }
            map.put(ch, i);
            max = Math.max(max, i - left + 1);
        }
        System.out.println(max);
    }
}
