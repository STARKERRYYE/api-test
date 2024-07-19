import com.example.api.Dijsktra;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijTest {
    @Test
    public void f () {

        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();
        int[][] arr = new int[v][v];

        for (int i = 0;i < v;i ++) {
            Arrays.fill(arr[i], 99999);
        }
        for (int i = 0;i < e;i ++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int weight = scanner.nextInt();
            arr[start][end] = weight;
        }
        for (int i = 0;i < v;i ++) {
            arr[i][i] = 0;
        }
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        Dijsktra d = new Dijsktra(v);

        Integer res = d.dij(arr, from, to);
        assertEquals(65, res);
    }
}
