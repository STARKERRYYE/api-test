import com.example.api.LocalTestC;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalTestClass1 {
    @Resource
    private LocalTestC localTestC;

    @Test
    public void test() {
        assertEquals(5, new LocalTestC().getString("rrr"));
    }
}
