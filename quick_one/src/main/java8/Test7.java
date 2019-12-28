import org.junit.Test;

import java.util.ArrayList;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/25.
 * @Description:
 */
public class Test7 {
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
        //a,b 降序排列，a<b : return -1； a>b:return 1;a=b: return 0；再按照id降序
        list.sort((a,b)->{
            if(a.getAge()>b.getAge()){
                return -1;
            }else if(a.getAge()==b.getAge()) {
                if(Integer.parseInt(a.getId())>Integer.parseInt(b.getId())){
                    return -1;
                }else{
                    return 1;
                }
            }else {
                return 1;
            }
        });
        list.forEach(System.out::println);
//        User(id=8, name=1, age=89)
//        User(id=5, name=1, age=47)
//        User(id=7, name=1, age=46)
//        User(id=4, name=1, age=45)
//        User(id=2, name=1, age=31)
//        User(id=1, name=1, age=29)
//        User(id=6, name=1, age=12)
//        User(id=3, name=1, age=12)
    }
}
