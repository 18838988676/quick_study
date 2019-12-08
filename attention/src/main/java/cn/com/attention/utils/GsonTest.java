package cn.com.attention.utils;


import cn.com.attention.entity.Person;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*Gson toJson 与 fromJson 
     Gson 提供了 fromJson() 和 toJson() 两个直接用于解析和生成的方法，前者实现反序列化，后者实现了序列化。同时每个方法都提供了重载方法.。

     Gson 对象的 toJson 方法可以将基本数据类型、以及 POJO 对象、List、Map 等转为 json 格式的字符串

     Gson 对象的 fromJson 方法做与 toJson 相反的操作，将 json 格式的字符串转为基本数据类型、 POJO 对象、List、Map 等
*/
@SpringBootTest
@RunWith(SpringRunner.class)
public class GsonTest {

    @Autowired
    private Gson gson;

    /**
     * json 字符串 -> 转为基本数据类型
     */
    @Test
    public void parserJsonToBasicData() {
        /**
         *  <T> T fromJson(String json, Class<T> classOfT)
         *  json：被解析的 json 字符串
         *  classOfT：解析结果的类型，可以是基本类型，也可以是 POJO 对象类型
         */
        int i = gson.fromJson("100", int.class);
        double d = gson.fromJson("100.99", Double.class);
        boolean b = gson.fromJson("true", boolean.class);
        String s = gson.fromJson("爱你", String.class);
        System.out.print(i + "\t" + d + "\t" + b + "\t" + s);
        //输出：100	100.99	true	爱你
    }


    /**
     * 基本类型 -> 转为 Json 字符串
     */
    @Test
    public void parserBasicDataToJson() {
        /**
         * String toJson(Object src)
         * 将对象转为 json，如 基本数据、POJO 对象、以及 Map、List 等
         */
        String jsonNumber = gson.toJson(100);
        String jsonDouble = gson.toJson(100.99);
        String jsonBoolean = gson.toJson(false);
        String jsonString = gson.toJson("Tiger");
        System.out.println(jsonNumber + "\t" + jsonDouble + "\t" + jsonBoolean + "\t" + jsonString);
        //输出：100	100.99	false	"Tiger"
    }


    /**
     * 数组格式的 Json 字符串 - > 转为 数组
     */
    @Test
    public void parserJsonStrToArray() {
        String jsonArrayStr = "[\"Java\",\"Android\",\"IOS\",\"A\"]";
        String[] strings = gson.fromJson(jsonArrayStr, String[].class);
        for (int i = 0; i < strings.length; i++) {
            System.out.print((i + 1) + ":" + strings[i] + "\t");
        }
        //输出：1:Java	2:Android	3:IOS
    }


    /**
     * 数组格式的 Json 字符串 - > 转为 List 列表
     */
    @Test
    public void parserJsonStrToList() {
        String jsonArray = "[\"Java1\",\"Android2\",\"IOS3\"]";
        /**
         * 如果解析结果是普通对象，如 单纯的 POJO，则可以使用 fromJson(String json, Class<T> classOfT)
         * 如果解析结果是复杂类型，如 List<T> 这种，则应该使用 fromJson(String json, Type typeOfT)
         * json：被转换的 json 格式的字符串
         * typeOfT：解析结果类型
         */
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {
        }.getType());

        for (int i = 0; i < stringList.size(); i++) {
            System.out.print((i + 1) + ":" + stringList.get(i) + "\t");
        }
        //输出：1:Java1	2:Android2	3:IOS3
    }

    /**
     * Java POJO 对象 -> 转为 json 字符串
     */
    @Test
    public void parserPOJOToJson() {
      //加上这巨后，空的也被序列化，不加的话  默认对空值不进行序列化
        gson=  gson.newBuilder().serializeNulls().create();
        gson=gson.newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//      gson=gson.newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        Person person = new Person();
        person.setId(9527);
        person.setName("华安");
        person.setBirthday(new Date());
//            person.setIsMarry(true);

        /**
         * String toJson(Object src)
         * 将对象转为 json，如 基本数据、POJO 对象、以及 Map、List 等
         * 注意：如果 POJO 对象某个属性的值为 null，则 toJson(Object src) 默认不会对它进行转化
         * 结果字符串中不会出现此属性
         */
        String jsonUser = gson.toJson(person);
        System.out.println(jsonUser);
        //输出：{"pId":9527,"pName":"华安","birthday":"Nov 23, 2018 1:50:56 PM","isMarry":true}
    }


    /**
     * Json 字符串 -> 转为 POJO 对象
     */
    @Test
    public void parserJsonToPOJO() {
        /**符合 json 格式的字符串*/
        String personJson = "{\"id\":9527,\"name\":\"华安\",\"birthday\":\"Nov 23, 2018 1:50:56 PM\",\"isMarry\":true}";

        /**
         *  <T> T fromJson(String json, Class<T> classOfT)
         *  json：被解析的 json 字符串
         *  classOfT：解析结果的类型，可以是基本类型，也可以是 POJO 对象类型，gson 会自动转换
         */
        Person person = gson.fromJson(personJson, Person.class);
        System.out.println(person);
        //输出：Person{birthday=Fri Nov 23 13:50:56 CST 2018, pId=null, pName='华安', isMarry=true}

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String personBirthday = dateFormat.format(person.getBirthday());
        System.out.println("用户生日：" + personBirthday);
        //输出：用户生日：2018-11-23 13:50:56
        //可见日期字符串也能完美转换
    }

    /**
     * List 集合 -> 转为 json 字符串
     * list 会被解析成 Json 数组，List 中的元素被解析成 json
     */
    @Test
    public void parserPOJOListToJson() {
        Person person = new Person();
        person.setId(9527);
        person.setName("华安");
        person.setBirthday(new Date());
        person.setIsMarry(true);

        Person person2 = new Person();
        person2.setId(8866);
        person2.setName("宁王");

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person2);
        /**
         * String toJson(Object src)
         * 将对象转为 json，如 基本数据、POJO 对象、以及 Map、List 等
         * 注意：如果 POJO 对象某个属性的值为 null，则 toJson(Object src) 默认不会对它进行转化
         * 结果字符串中不会出现此属性
         */
        String personListJson = gson.toJson(personList);
        System.out.println(personListJson);
        //输出：[{"pId":9527,"pName":"华安","birthday":"Nov 23, 2018 2:05:58 PM","isMarry":true},{"pId":8866,"pName":"宁王","isMarry":false}]
    }

    @Test
    public void test() {
        String json = "[{\"id\":9527,\"name\":\"华安\",\"birthday\":\"Dec 7, 2019 4:15:31 PM\",\"isMarry\":true},{\"id\":8866,\"name\":\"宁王\"}]";
        List<Person> ps = gson.fromJson(json, new TypeToken<List<Person>>() {
        }.getType());
        System.out.println(ps);
    }

}