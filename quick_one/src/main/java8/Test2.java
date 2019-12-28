import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/23.
 * @Description:
 */
public class Test2 {
    @Test
    public void test() {

        /*===========================================================*/
        //方便转换成list集合
        String a = "123";
        List<String> list = Collections.singletonList(a);
        list.stream().forEach(e -> {
            System.out.println(e);
        });

        String b = "1235";
        Map<String, Object> map = Collections.singletonMap("id", b);
//        map.put("name","wmc");  java.lang.UnsupportedOperationException
        map.forEach((key, value) -> {
            System.out.println("key:" + key + "-- value:" + value);
        });

        /*===========================================================*/
        Map<String, Object> map2 = new HashMap<>();
        map2.put("1", "001");
        map2.put("2", "002");
        map2.put("3", "003");
        map2.put("4", "004");
        map2.put("5", "005");
        map2.put("6", "006");

        map2.forEach((key, value) -> {
            System.out.println("key:" + key + ",value:" + value);
//            key:1,value:001
//            key:2,value:002
//            key:3,value:003
//            key:4,value:004
//            key:5,value:005
//            key:6,value:006
        });
    }


    @Test
    public void test2() {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("1", "001");
        map2.put("2", "002");
        map2.put("3", "003");
        map2.put("4", "004");
        map2.put("5", "005");
        map2.put("6", "006");

        map2.forEach((a, b) -> {
            if (a.equals("5")) {
                //key:5,value:005
                System.out.println("key:" + a + ",value:" + b);
            }
        });

    }



    @Test
    public void tet() {
        List<String> alpha = Arrays.asList("a", "b", "c", "d");

        List<String> newlist=new ArrayList<>();
        for (String str:alpha){
            newlist.add(str.toUpperCase());
        }
        System.out.println(alpha);
        System.out.println(newlist);

        System.out.println("==============after java 8:1===================");

        List<String> newJava8=alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(newJava8);//[A, B, C, D]
        newJava8=alpha.stream().map(n->n+"***").collect(Collectors.toList());
        System.out.println(newJava8);//[a***, b***, c***, d***]
        System.out.println("==============after java 8:2===================");
        List<Integer> in=Arrays.asList(1,2,3,4,5);
        List<Integer> inJava8=in.stream().map(n->n*2).collect(Collectors.toList());
        System.out.println(inJava8);//[2, 4, 6, 8, 10]


    }


}
