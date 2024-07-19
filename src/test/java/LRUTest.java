import com.example.api.LRUAlgorithm;
import org.junit.jupiter.api.Test;

public class LRUTest {

    @Test
    public void testLRU() {
        LRUAlgorithm lruCache = new LRUAlgorithm(3);
        lruCache.put(1, 1);
        lruCache.put(2, 1);
        lruCache.put(3, 1);
        lruCache.get(1);
        lruCache.put(4, 1);
        System.out.println(lruCache);

    }
}
