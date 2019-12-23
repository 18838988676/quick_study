import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/23.
 * @Description:
 */
public class Test {
    @org.junit.Test
    public void test() {
        System.out.println("12");
        System.out.println(TestEnum.ONE.getTitle());//001
        System.out.println(TestEnum.ONE.getId());//1
        Arrays.stream(TestEnum.values()).forEach(e->{
            System.out.println(e.getId()+"=="+e.getTitle());

//            1==001
//            3==003
//            2==002

        });
        System.out.println(TestEnum.valueOf("ONE").name()+"=="+TestEnum.valueOf("ONE").getTitle());//ONE==001

        User user=null;
        if(null==user){
            user=new User();
            System.out.println(user);
        }else {

        }

        List list=null;
        if(list==null){
            System.out.println("null");
            list=new ArrayList(12);
            if(list.isEmpty()){
                System.out.println("list.isEmpty:"+list.isEmpty());
                list.add("123");
                if(list.isEmpty()){
                    System.out.println("list.isEmpty:"+list.isEmpty());
                }else {
                    System.out.println("list.is not empty");
                }
            }
        }

    }
}
