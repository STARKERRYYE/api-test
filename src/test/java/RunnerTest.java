import com.example.api.Runner;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunnerTest {
    @Test
    public void f () {
//        Runner.Job j = new Runner.Job("f", "sdf", 20);
//        System.out.println(j.checkStatus());
//        assert(StringUtils.isBlank(res));
        Runner r1 = new Runner();
        Runner r2 = new Runner();
        r1.submitNewJob("", "");
        r2.submitNewJob("", "");
    }

}
