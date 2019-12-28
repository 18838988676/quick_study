import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/24.
 * @Description:
 */
public class Test5 {
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("aaa bbb ccc");
        list.add("ddd eee fff");
        list.add("ggg hhh iii");

        list = list.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).collect(Collectors.toList());
        list.forEach(e-> System.out.println(e));

        List<User> users=new ArrayList<>();
        users.add(new User("a","1a",2));
        users.add(new User("b","1b",4));
        users.add(new User("c","1c",2));
        users.add(new User("d","1d",2));
        int sum=users.stream().map(User::getAge).reduce(0,(a, b)->a+b);
//        int sum=users.stream().map(User::getAge).reduce(0,Integer::sum);
        System.out.println(sum);//10岁； 计算年龄总和

        try {

        }catch (){}finally {

        }
        int i=users.stream().map(User::getAge).reduce(1,(a, b)->a*b);
        System.out.println(i);//32岁； 计算年龄乘积
        Optional<Integer> su = users.stream().map(User::getAge).reduce(Integer::sum);
        users.stream().forEach(System.out::println);
//        User(id=a, name=1a, age=2)
//        User(id=b, name=1b, age=4)
//        User(id=c, name=1c, age=2)
//        User(id=d, name=1d, age=2)



        Optional<Integer> su2= users.stream().mapToInt(User::getAge).boxed().reduce(Integer::sum);
        System.out.println(su2.get());//10 岁   将
       int su4= users.stream().mapToInt(User::getAge).boxed().reduce(0,Integer::sum);
        System.out.println(su4);//10岁  由于age是Integer 类型，相加时 需要转换为int类型 比较浪费效率，所jav8可以转换；


    }


}
