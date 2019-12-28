import com.botbrain.timer.core.model.XxlJobGroup;
import com.botbrain.timer.dao.XxlJobGroupDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/24.
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test10 {
    @Autowired
    private XxlJobGroupDao xxlJobGroupDao;
    @org.junit.Test
    public void test() {
        List<XxlJobGroup> inst= new ArrayList<>();
        XxlJobGroup xxlJobGroup=new XxlJobGroup();
        xxlJobGroup.setAddressList("2342");
        xxlJobGroup.setTitle("test");
        xxlJobGroup.setOrder(2);
        xxlJobGroup.setAppName("fasdfa");
        inst.add(xxlJobGroup);
         inst.stream().forEach(xxlJobGroupDao::save);
    }
}
