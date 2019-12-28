import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/25.
 * @Description:
 */
public class Test8 {
    @Test
    public void test() {

        User user=new User("1","1",29);
        User user1=new User("2","1",31);
        User user2=new User("3","1",12);
        User user3=new User("4","1",45);
        User user4=new User("5","1",47);
        User user5=new User("6","1",12);
        User user6=new User("7","1",46);
        User user7=new User("8","1",89);
        ArrayList<User> list=new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);

        Collections.singletonList(user7);//将非集合直接封装成list集合；
//        Collections.singletonMap() map 将非集合直接封装成map集合；
//        Collections.singleton() set将非集合直接封装成set集合；
        List<User> users = Collections.singletonList(user7);//
        // ALT + ENDER 只能提示；
        for (User user8 : users) {
            System.out.println(user8);
        }


    }
}
