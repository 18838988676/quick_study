import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/24.
 * @Description:
 */
public class Test6 {
    @Test
    public void test() {

        List<User> users = new ArrayList<>();
        users.add(new User("a", "1a", 2));
        users.add(new User("b", "1b", 4));
        users.add(new User("c", "1c", 2));
        users.add(new User("d", "1d", 2));

        OptionalInt optionalInt = users.stream().mapToInt(User::getAge).max();
        System.out.println(optionalInt);//OptionalInt[4]
        System.out.println(optionalInt.getAsInt());//4

        IntStream intStream = IntStream.rangeClosed(1, 5);

        // intStream.forEach(System.out::print);//12345
        System.out.println(intStream.sum());

        Stream<String> stream = Stream.of("aaa", "bbb", "ccc");
        Arrays.asList("aaa", "bbb").stream();
        String[] a = new String[]{"aaa", "bbb", "ccc"};
        Arrays.asList(a, 2, 3).stream();


    }

    @Test
    public void test2() {
        try {
//            String content = new String(Files.readAllBytes(Paths.get("_template.txt")),StandardCharsets.ISO_8859_1);
//            Stream<String> lines = Files.lines(Paths.get("_template.txt"),StandardCharsets.ISO_8859_1);
            Stream<String> filestream = Files.lines(Paths.get("d:/a.txt"), StandardCharsets.UTF_8);
            List<String> list = filestream.collect(Collectors.toList());
            for (String s : list) {
                System.out.println(s);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
