package cn.com.attention;

import cn.com.attention.entity.Person;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
JsonObject、JsonArray、JsonParser
     JsonObject 表示 json 对象、JsonArray 表示 json 对象数组、JsonParser 表示 json 解析，将 json 格式的字符串解析为 JsonObject、JsonArray 对象。


*/
@SpringBootTest
@RunWith(SpringRunner.class)
public class GsonTest2 {

    @Autowired
    private Gson gson;

    /**
     * Json 格式字符串 -> 解析为 JSON 对象
     */
    @Test
    public  void parserJsonStrToJsonObj() {
        String jsonStr = "{\"Id\":9527,\"Name\":\"华安\",\"birthday\":\"Nov 23, 2018 1:50:56 PM\",\"isMarry\":true}";

        JsonParser jsonParser = new JsonParser();
        /**JsonElement parse(String json)
         * 如果被解析的字符串不符合 json 格式，则抛出异常*/
        JsonObject jsonObject = jsonParser.parse(jsonStr).getAsJsonObject();

        /**JsonElement get(String memberName)
         * 注意：如果 get 的 key 不存在，则返回 null，如果不加判断而进行取值的话，会抛：java.lang.NullPointerException
         * */
        Integer id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();

        System.out.println(jsonObject);
        //输出：{"id":9527,"name":"华安","birthday":"Nov 23, 2018 1:50:56 PM","isMarry":true}
        System.out.println(id + "\t" + name);
        //输出：9527	华安
    }

    /**
     * Json 数组字符串 -> 解析为 JSON 数组对象
     */
    @Test
    public  void parserJsonStrToJsonArray() {
        String jsonArrayStr = "[{\"id\":9527,\"name\":\"华安\",\"isMarry\":true},{\"id\":8866,\"name\":\"宁王\",\"isMarry\":false}]";
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(jsonArrayStr).getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            System.out.println((i + 1) + ":" + jsonObject + "\t\t" + jsonObject.get("name").getAsString());
        }
        //输出：1:{"id":9527,"name":"华安","isMarry":true}		华安
        //输出：2:{"id":8866,"name":"宁王","isMarry":false}		宁王
    }


    /**
     * 手动 创建 JSON对象 并添加基本类型属性
     */
    @Test
    public  void createJsonObj() {
        /**
         * JsonObject 添加基本类型属性使用 addProperty(String property, Number value)、 addProperty(String property, String value) 等
         * JsonObject 添加复杂类型属性使用 add(String property, JsonElement value)
         * JsonObject 与 JsonArray 都是 JsonElement 类的子类
         * 提示：如果添加的 key 已经存在，则后面的会覆盖前面的
         */
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 9527);
        jsonObject.addProperty("name", "华安");
        jsonObject.addProperty("isMarry", true);

        /**
         * JsonObject 取值使用 JsonElement get(String memberName)，根据 JsonElement 可以获取任意类型
         * JsonObject 可以直接获取 JsonArray：JsonArray getAsJsonArray(String memberName)
         * JsonObject 可以直接获取 JsonObject：JsonObject getAsJsonObject(String memberName)
         */
        System.out.println(jsonObject + "\t" + jsonObject.get("name").getAsString());
        //输出：{"id":9527,"name":"华安","isMarry":true}	华安
    }


    /**
     * 创建 JSON 数组
     */
    @Test
    public  void createJsonArray() {
        String json1 = "{\"id\":9527,\"name\":\"华安\",\"isMarry\":true}";
        String json2 = "{\"id\":1200,\"name\":\"安禄山\",\"isMarry\":false}";

        JsonObject jsonObject1 = new JsonParser().parse(json1).getAsJsonObject();
        JsonObject jsonObject2 = new JsonParser().parse(json2).getAsJsonObject();

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);

        System.out.println(jsonArray);
        //输出：[{"id":9527,"name":"华安","isMarry":true},{"id":1200,"name":"安禄山","isMarry":false}]
    }


    /**
     * 删除 JsonObject 的某个属性
     */
    @Test
    public  void delJsonObjproperty() {
        String json = "{\"id\":9527,\"name\":\"华安\",\"isMarry\":true}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        System.out.println("删除前：" + jsonObject);
        //输出：删除前：{"id":9527,"name":"华安","isMarry":true}
        /**
         * JsonElement remove(String property)
         * 用于删除 JsonObject 的属性，返回被删除的属性的值，原 JsonObject 会改变
         * 与get取值同理，如果 remove 的属性值不存在，则返回 null
         */
        String delProperty = jsonObject.remove("name").getAsString();

        System.out.println("删除 " + delProperty + " 后：" + jsonObject);
        //输出：删除 华安 后：{"id":9527,"isMarry":true}
    }


    /**
     * 修改 JsonObject 属性，与添加一样使用 addProperty，当 key 已经存在时，会覆盖旧值
     */
    @Test
    public  void updateJsonObjproperty() {
        String json = "{\"currentOnlineNumber\":\"101\",\"name\":\"汪茂雄\"}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        System.out.println("修改前：" + jsonObject);

        jsonObject.addProperty("name", "赵丽颖");
        System.out.println("修改后：" + jsonObject);
    }

    /**
     * 获取 JsonObject 属性值
     */
    @Test
    public  void getJsonObjproperty() {
        String json = "{\"id\":9527,\"name\":\"华安\",\"Dog\":{\"dName\":\"小黄\",\"color\":\"yellow\"}}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        System.out.println("操作对象：" + jsonObject);

        /**
         * JsonElement get(String memberName)：如果 memberName 不存在，则返回 null
         * 所以除非应用中明确知道key存在，否则应该加以判断
         */
        Integer id = jsonObject.get("id").getAsInt();
        System.out.println("id=" + id);

        /** boolean has(String memberName)
         * 判断 JsonObject 中是否存在某个 key*/
        if (!jsonObject.has("name")) {
            System.out.println("name 属性不存在...");
        }

        /**
         * JsonObject 取值使用 JsonElement get(String memberName)，根据 JsonElement 可以获取任意类型
         * JsonObject 可以直接获取 JsonArray：JsonArray getAsJsonArray(String memberName)
         * JsonObject 可以直接获取 JsonObject：JsonObject getAsJsonObject(String memberName)
         */
        JsonObject dogJson = jsonObject.getAsJsonObject("Dog");
        System.out.println(dogJson);
    }

    /**
     * 实际应用中，如果业务比较复杂，则通常都会嵌套 json，如下所示：
     * [{"id":110,"name":"华安","Dog":{"dName":"小黄","color":"yellow"}},{"id":120,"name":"安禄山","Dog":{"dName":"阿毛","color":"red"}}]
     */
    @Test
    public  void complexParsen() {
        /**两只小狗 json 字符串*/
        String dog1JsonStr = "{\"dName\":\"小黄\",\"color\":\"yellow\"}";
        String dog2JsonStr = "{\"dName\":\"阿毛\",\"color\":\"red\"}";

        /**将 json 字符串解析为 JsonObject 对象*/
        JsonObject dog1Json = new JsonParser().parse(dog1JsonStr).getAsJsonObject();
        JsonObject dog2Json = new JsonParser().parse(dog2JsonStr).getAsJsonObject();

        /**创建两个用户的 JsonObject 对象*/
        JsonObject user1Json = new JsonObject();
        JsonObject user2Json = new JsonObject();

        /**添加普通值*/
        user1Json.addProperty("id", 110);
        user1Json.addProperty("name", "华安");
        /**添加JsonObject对象
         * 注意：添加的对象，而不应该是符合 json 格式的字符串*/
        user1Json.add("Dog", dog1Json);

        user2Json.addProperty("id", 120);
        user2Json.addProperty("name", "阿毛");
        user2Json.add("Dog", dog2Json);

        /**创建 JsonArray 用于存放 JsonObject
         * 同样添加的应该是对象，而不应该是符合格式的字符串*/
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(user1Json);
        jsonArray.add(user2Json);

        System.out.println(jsonArray);
        //输出：[{"id":110,"name":"华安","Dog":{"dName":"小黄","color":"yellow"}},{"id":120,"name":"阿毛","Dog":{"dName":"阿毛","color":"red"}}]
    }



    /*字段值为 null 时是否序列化
     Gson 在 toJson 的时候，默认是不会对属性值为 null 的字段进行序列化的，即结果中不会包含它，可以使用 GsonBuild  的 serializeNulls 方法构建 Gson，这样就会对属性值为 null 的字段进行序列化。

     实际应用中，是否对属性值为 null 的字段进行序列化，根据需求定即可，建议进行序列化。
*/


    /**
     * 对象中为 null 的字段，是否实行序列化，为了降低 bug，应该保持一致
     * 实际应用中，前后台数据交互，系统之间数据传递时，如果接收端在获取某个值的时候不存在，而发送端以为自己以及发送了，
     * 其实就是字段值可能为 null，发送端发送的时候，导致没有进行序列化
     */
    @Test
    public void parserPOJOToJsonContainNull() {
        /**
         * Person 实体一共4个字段：Integer pId、String pName、Date birthday、Boolean isMarry
         * 现在它的属性全部不进行赋值
         */
        Person person = new Person();

        /** 不对属性值为 null 的字段进行序列化，转换结果会为空*/
        String personStr = new Gson().toJson(person);

        /** 对属性值为 null 的字段进行序列化*/
        String personStrFinal = new GsonBuilder().serializeNulls().create().toJson(person);

        System.out.println(personStr);
        //输出：{}
        System.out.println(personStrFinal);
        //输出：{"pId":null,"pName":null,"birthday":null,"isMarry":null}

        /** 对于字段值为 null 的字符串，解析的时候也需要注意
         * JsonElement get(String memberName) 的时候返回的就是 null
         */
        JsonObject jsonObject = new JsonParser().parse(personStrFinal).getAsJsonObject();
        System.out.println(jsonObject.get("pName"));//输出：null

        /**判断元素是否为 null*/
        System.out.println(jsonObject.get("pName").isJsonNull());
        //输出：true
    }

}