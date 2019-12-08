package cn.com.attention.utils;

import cn.com.attention.entity.Student;
import cn.com.attention.entity.Student2;
import cn.com.attention.entity.Student3;
import cn.com.attention.entity.Student4;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:

BeanUtils用法
对象的拷贝，BeanUtils.copyProperties(Object dest,Object orig)
对象属性的拷贝，BeanUtils.copyProperties(Object bean,String name,Object value)或者BeanUtils.setProperty(Object bean,String name,Object value)
map数据封装到Javabean，populate(Object bean, Map<String,? extends Object> properties)
 *
 */
@SpringBootTest
public class BeanUtilsTest {
//    对象的拷贝
    @Test
    public void test() throws Exception {
        //创建对象
        Student3 s=new Student3();
        /**
         * 组件对JavaBean的操作
         * bean:javaBean对象
         * name:对象的属性名称
         * value:对象的属性值
         */
        //1.实现对象的属性拷贝， 对于基本数据类型，会自动进行类型转换
        BeanUtils.copyProperty(s, "id","2018100712");

        //2.实现对象之间的拷贝:Object dest<---Object orig
        Student3 s2=new Student3();
        BeanUtils.copyProperties(s2, s);
        System.out.println(s2);
    }

//    对象属性的拷贝
    @Test
    public void test2() throws Exception {
        //创建对象
        Student3 s=new Student3();
		/*一般的操作
		s.setId("1221212");
		s.setName("老王");
		System.out.println(s);
		*/
        //1.实现对象的属性拷贝， 对于基本数据类型，会自动进行类型转换
        BeanUtils.copyProperty(s, "id","2018100712");
        System.out.println(s);
    }

    //map数据封装到javaBean
    @Test
    public void test3() throws Exception {
        //创建对象
        Student3 s2 = new Student3();
        //1.map的数据拷贝到对象中去
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "12233");
        map.put("name", "老王");
        map.put("sex", "男");
        BeanUtils.populate(s2, map);
        System.out.println(s2);
    }

    @Test
    public void test4() throws Exception {
        //假设网页表单提交过来的数据
        String name = "老王";
        String id = "121212";
        String date = "2018-10-11";
        Student2 s = new Student2();
        //1.自定义：注册日期类型转换器去复写ConvertUtils里面的register(Converter converter, Class<?> clazz)方法
        ConvertUtils.register(new Converter() {
            //修改第三方jar引入方法的参数时候，可以关联源码，ctrl选择该类，点进去，选择Attach source--->external file
            @Override
            public Object convert(Class type, Object value) {
                //判断
                if (value == null || "".equals(value.toString().trim())) {
                    return null;
                }
                if (type != Date.class) {
                    return null;
                }
                try {
                    //字符串转换为日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.parse(value.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, Date.class);
        //把表单的数据封装到对象中去
        BeanUtils.copyProperty(s, "name", name);
        BeanUtils.copyProperty(s, "id", id);
        BeanUtils.copyProperty(s, "d", date);
        System.out.println(s);
    }



    @Test
    public void test5() throws Exception {
        //利用组件提供的日期类型转换器，提供一个实现类
        //假设表单的数据
        String name = "老王";
        String id = "121212";
        //当日期字符串出现空字符串或者出现空格的情况，会报错org.apache.commons.beanutils.ConversionException
        String date = "2018-09-12";
        Student2 s = new Student2();
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        //把表单的数据封装到对象中去
        BeanUtils.copyProperty(s, "name", name);
        BeanUtils.copyProperty(s, "id", id);
        BeanUtils.copyProperty(s, "d", date);
        System.out.println(s);
    }



    @Test
    public void test01() throws Exception {
        Student4 stu1 = new Student4();
        Student4 stu2 = new Student4();
        stu2.setNo("TEST001");
        stu2.setName("Jack Chen");

        //复制属性
        System.out.println(stu1);
        System.out.println("------------------------");
        BeanUtils.copyProperties(stu1, stu2);
        System.out.println(stu1);

//设置属性的值
        BeanUtils.copyProperty(stu1, "major", "机械设计");
        System.out.println("------------------------");
        System.out.println(stu1);

 //将javaBean转化成Map
        Map<String, String> describe = BeanUtils.describe(stu1);
        System.out.println("------------------------");
        for (Map.Entry<String, String> entry : describe.entrySet()) {
            System.out.println("K:"+entry.getKey()+"--V:"+entry.getValue());
        }

    //将Map转化成javaBean
        Map<String , String> map = new HashMap<>();
        map.put("no", "TEST002");
        map.put("name", "Blueth Li");
        map.put("major", "Kong Fu");
        Student4 stu3 = new Student4();
        BeanUtils.populate(stu3, map);
        System.out.println("------------------------");
        System.out.println(stu3);
    }
}

class GetDataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取所有参数
        Map<String, String[]> map = request.getParameterMap();
        Student2 s=new Student2();
        try {
            BeanUtils.populate(s, map);
            //测试,输出封装的结果
            System.out.println(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}