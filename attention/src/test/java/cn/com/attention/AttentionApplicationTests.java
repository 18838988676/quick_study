package cn.com.attention;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttentionApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("柳柴".hashCode());
        System.out.println("柴柕".hashCode());
    }

}
