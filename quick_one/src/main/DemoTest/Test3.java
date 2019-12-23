import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/23.
 * @Description:
 */
public class Test3 {
    private static List<StaffPublic> convertToStaffPublic(List<Staff> staff) {

        List<StaffPublic> result = new ArrayList<>();

        for (Staff temp : staff) {

            StaffPublic obj = new StaffPublic();
            obj.setName(temp.getName());
            obj.setAge(temp.getAge());
            if ("mkyong".equals(temp.getName())) {
                obj.setExtra("this field is for mkyong only!");
            }
            result.add(obj);
        }
        return result;
    }

    @Test
    public void test() {
        List<User> list = Arrays.asList(new User("1", "23sd", 23),
                new User("1", "werqwe23", 23),
                new User("1", "vbsadfd23", 23),
                new User("1", "额外若群翁23", 23));


        List<String> list1 = list.stream().map(e -> e.getName()).collect(Collectors.toList());
        System.out.println(list1);//[23sd, werqwe23, vbsadfd23, 额外若群翁23]
    }

    @Test
    public void test2() {
        //BeforeJava8
        List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );
        List<StaffPublic> result = convertToStaffPublic(staff);
        System.out.println(result);
//        [StaffPublic(name=mkyong, age=30, extra=this field is for mkyong only!), StaffPublic(name=jack, age=27, extra=null), StaffPublic(name=lawrence, age=33, extra=null)]
    }

    @Test
    public void test3() {
        List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );

        // convert inside the map() method directly.
        List<StaffPublic> result = staff.stream().map(temp -> {
            StaffPublic obj = new StaffPublic();
            obj.setName(temp.getName());
            obj.setAge(temp.getAge());
            if ("mkyong".equals(temp.getName())) {
                obj.setExtra("this field is for mkyong only!");
            }
            return obj;
        }).collect(Collectors.toList());

        System.out.println(result);
//[StaffPublic(name=mkyong, age=30, extra=this field is for mkyong only!), StaffPublic(name=jack, age=27, extra=null), StaffPublic(name=lawrence, age=33, extra=null)]
    }
}

