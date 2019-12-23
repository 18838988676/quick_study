package cn.com.attention.utils.utilsresponse;

import com.qcloud.cmq.Json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/10.
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @org.junit.Test
    public void test() {
        OsCheckEntity osCheckEntity=new OsCheckEntity(true,"2w",1);
        JSONObject jsonObject1=new JSONObject(osCheckEntity);
        String json="{\"osk\":\"2w\",\"isAdd\":true,\"jobType\":1}";
        JSONObject jsonObj=new JSONObject(json);
        OsCheckEntity osCheckEntity2=new OsCheckEntity(jsonObj.getBoolean("isAdd"),jsonObj.getString("osk"),jsonObj.getInt("jobType"));
        System.out.println(osCheckEntity2);
    }
}
