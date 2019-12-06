import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/5.
 * @Description:
 */
public class Timers {

    @Test
    public void test() {
        Date date=new Date();

        System.out.println(transDate(date));
    }
    private String transDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期时间格式
        return dateFormat.format(date);
    }
}
