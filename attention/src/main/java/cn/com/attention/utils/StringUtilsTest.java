package cn.com.attention.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.StringUtils;
/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StringUtilsTest {
    @Test
    public void test() {


    }

    /*
    =================================

    org.apache.commons.lang.StringUtils中方法的操作对象是java.lang.String类型的对象，是JDK提供的String类型操作方法的补充，并且是null安全的(即如果输入参数String为null则不会抛出NullPointerException，而是做了相应处理，例如，如果输入为null则返回也是null等，具体可以查看源代码)。
除了构造器，StringUtils中一共有130多个方法，并且都是static的，
所以我们可以这样调用StringUtils.xxx()。
下面分别对一些常用方法做简要介绍：
1. public static boolean isEmpty(String str)
判断某字符串是否为空，为空的标准是str == null 或 str.length() == 0
下面是示例：
StringUtils.isEmpty(null) = true
StringUtils.isEmpty(“”) = true
StringUtils.isEmpty(” “) = false
StringUtils.isEmpty(” “) = false
StringUtils.isEmpty(“bob”) = false
StringUtils.isEmpty(” bob “) = false
2. public static boolean isNotEmpty(String str)
判断某字符串是否非空，等于!isEmpty(String str)
下面是示例：
StringUtils.isNotEmpty(null) = false
StringUtils.isNotEmpty(“”) = false
StringUtils.isNotEmpty(” “) = true
StringUtils.isNotEmpty(” “) = true
StringUtils.isNotEmpty(“bob”) = true
StringUtils.isNotEmpty(” bob “) = true
3. public static boolean isBlank(String str)
判断某字符串是否为空或长度为0或由空白符(whitespace)构成
下面是示例：
StringUtils.isBlank(null) = true
StringUtils.isBlank(“”) = true
StringUtils.isBlank(” “) = true
StringUtils.isBlank(” “) = true
StringUtils.isBlank(“\t \n \f \r”) = true
StringUtils.isBlank(“\b”) = false
StringUtils.isBlank(“bob”) = false
StringUtils.isBlank(” bob “) = false
4. public static boolean isNotBlank(String str)
判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成，
等于!isBlank(String str)
下面是示例：
StringUtils.isNotBlank(null) = false
StringUtils.isNotBlank(“”) = false
StringUtils.isNotBlank(” “) = false
StringUtils.isNotBlank(” “) = false
StringUtils.isNotBlank(“\t \n \f \r”) = false
StringUtils.isNotBlank(“\b”) = true
StringUtils.isNotBlank(“bob”) = true
StringUtils.isNotBlank(” bob “) = true
5. public static String trim(String str)
去掉字符串两端的控制符(control characters, char <= 32)
如果输入为null则返回null
下面是示例：
StringUtils.trim(null) = null
StringUtils.trim(“”) = “”
StringUtils.trim(” “) = “”
StringUtils.trim(” \b \t \n \f \r “) = “”
StringUtils.trim(” \n\tss \b”) = “ss”
StringUtils.trim(” d d dd “) = “d d dd”
StringUtils.trim(“dd “) = “dd”
StringUtils.trim(” dd “) = “dd”
6.public static String trimToNull(String str)
去掉字符串两端的控制符(control characters, char <= 32)
如果变为null或”“，则返回null
下面是示例：
StringUtils.trimToNull(null) = null
StringUtils.trimToNull(“”) = null
StringUtils.trimToNull(” “) = null
StringUtils.trimToNull(” \b \t \n \f \r “) = null
StringUtils.trimToNull(” \n\tss \b”) = “ss”
StringUtils.trimToNull(” d d dd “) = “d d dd”
StringUtils.trimToNull(“dd “) = “dd”
StringUtils.trimToNull(” dd “) = “dd”
7.public static String trimToEmpty(String str)
去掉字符串两端的控制符(control characters, char <= 32)
如果变为null或”“，则返回””
下面是示例：
StringUtils.trimToEmpty(null) = “”
StringUtils.trimToEmpty(“”) = “”
StringUtils.trimToEmpty(” “) = “”
StringUtils.trimToEmpty(” \b \t \n \f \r “) = “”
StringUtils.trimToEmpty(” \n\tss \b”) = “ss”
StringUtils.trimToEmpty(” d d dd “) = “d d dd”
StringUtils.trimToEmpty(“dd “) = “dd”
StringUtils.trimToEmpty(” dd “) = “dd”
8.public static String strip(String str)
去掉字符串两端的空白符(whitespace)，
如果输入为null则返回null
下面是示例(注意和trim()的区别)：
StringUtils.strip(null) = null
StringUtils.strip(“”) = “”
StringUtils.strip(” “) = “”
StringUtils.strip(” \b \t \n \f \r “) = “\b”
StringUtils.strip(” \n\tss \b”) = “ss \b”
StringUtils.strip(” d d dd “) = “d d dd”
StringUtils.strip(“dd “) = “dd”
StringUtils.strip(” dd “) = “dd”
9.public static String stripToNull(String str)
去掉字符串两端的空白符(whitespace)，
如果变为null或”“，则返回null
下面是示例(注意和trimToNull()的区别)：
StringUtils.stripToNull(null) = null
StringUtils.stripToNull(“”) = null
StringUtils.stripToNull(” “) = null
StringUtils.stripToNull(” \b \t \n \f \r “) = “\b”
StringUtils.stripToNull(” \n\tss \b”) = “ss \b”
StringUtils.stripToNull(” d d dd “) = “d d dd”
StringUtils.stripToNull(“dd “) = “dd”
StringUtils.stripToNull(” dd “) = “dd”
10.public static String stripToEmpty(String str)
去掉字符串两端的空白符(whitespace)，
如果变为null或”“，则返回””
下面是示例(注意和trimToEmpty()的区别)：
StringUtils.stripToNull(null) = “”
StringUtils.stripToNull(“”) = “”
StringUtils.stripToNull(” “) = “”
StringUtils.stripToNull(” \b \t \n \f \r “) = “\b”
StringUtils.stripToNull(” \n\tss \b”) = “ss \b”
StringUtils.stripToNull(” d d dd “) = “d d dd”
StringUtils.stripToNull(“dd “) = “dd”
StringUtils.stripToNull(” dd “) = “dd”

11.public static String strip(String str, String stripChars)
去掉str两端的在stripChars中的字符。
如果str为null或等于”“，则返回它本身；
如果stripChars为null或”“，则返回strip(String str)。
12.public static String stripStart(String str, String stripChars)
和11相似，去掉str前端的在stripChars中的字符。
13.public static String stripEnd(String str, String stripChars)
和11相似，去掉str末端的在stripChars中的字符。
14.public static String[] stripAll(String[] strs)
对字符串数组中的每个字符串进行strip(String str)，然后返回。
如果strs为null或strs长度为0，则返回strs本身
15.public static String[] stripAll(String[] strs, String stripChars)
对字符串数组中的每个字符串进行strip(String str, String stripChars)，然后返回。
如果strs为null或strs长度为0，则返回strs本身
16.public static boolean equals(String str1, String str2)
比较两个字符串是否相等，如果两个均为空则也认为相等。
17.public static boolean equalsIgnoreCase(String str1, String str2)
比较两个字符串是否相等，不区分大小写，如果两个均为空则也认为相等。
18.public static int indexOf(String str, char searchChar)
返回字符searchChar在字符串str中第一次出现的位置。
如果searchChar没有在str中出现则返回-1，
如果str为null或”“，则也返回-1
19.public static int indexOf(String str, char searchChar, int startPos)
返回字符searchChar从startPos开始在字符串str中第一次出现的位置。
如果从startPos开始searchChar没有在str中出现则返回-1，
如果str为null或”“，则也返回-1
20.public static int indexOf(String str, String searchStr)
返回字符串searchStr在字符串str中第一次出现的位置。
如果str为null或searchStr为null则返回-1，
如果searchStr为”“,且str为不为null，则返回0，
如果searchStr不在str中，则返回-1
21.public static int ordinalIndexOf(String str, String searchStr, int ordinal)
返回字符串searchStr在字符串str中第ordinal次出现的位置。
如果str=null或searchStr=null或ordinal<=0则返回-1
举例(*代表任意字符串)：
StringUtils.ordinalIndexOf(null, , ) = -1
StringUtils.ordinalIndexOf(, null, ) = -1
StringUtils.ordinalIndexOf(“”, “”, *) = 0
StringUtils.ordinalIndexOf(“aabaabaa”, “a”, 1) = 0
StringUtils.ordinalIndexOf(“aabaabaa”, “a”, 2) = 1
StringUtils.ordinalIndexOf(“aabaabaa”, “b”, 1) = 2
StringUtils.ordinalIndexOf(“aabaabaa”, “b”, 2) = 5
StringUtils.ordinalIndexOf(“aabaabaa”, “ab”, 1) = 1
StringUtils.ordinalIndexOf(“aabaabaa”, “ab”, 2) = 4
StringUtils.ordinalIndexOf(“aabaabaa”, “bc”, 1) = -1
StringUtils.ordinalIndexOf(“aabaabaa”, “”, 1) = 0
StringUtils.ordinalIndexOf(“aabaabaa”, “”, 2) = 0
22. public static int indexOf(String str, String searchStr, int startPos)
返回字符串searchStr从startPos开始在字符串str中第一次出现的位置。
举例(*代表任意字符串)：
StringUtils.indexOf(null, , ) = -1
StringUtils.indexOf(, null, ) = -1
StringUtils.indexOf(“”, “”, 0) = 0
StringUtils.indexOf(“aabaabaa”, “a”, 0) = 0
StringUtils.indexOf(“aabaabaa”, “b”, 0) = 2
StringUtils.indexOf(“aabaabaa”, “ab”, 0) = 1
StringUtils.indexOf(“aabaabaa”, “b”, 3) = 5
StringUtils.indexOf(“aabaabaa”, “b”, 9) = -1
StringUtils.indexOf(“aabaabaa”, “b”, -1) = 2
StringUtils.indexOf(“aabaabaa”, “”, 2) = 2
StringUtils.indexOf(“abc”, “”, 9) = 3
23.public static int lastIndexOf(String str, char searchChar)
基本原理同18。
24.public static int lastIndexOf(String str, char searchChar, int startPos)
基本原理同19。
25.public static int lastIndexOf(String str, String searchStr)
基本原理同20。
26.public static int lastIndexOf(String str, String searchStr, int startPos)
基本原理同22。

27.public static boolean contains(String str, char searchChar)
判断字符串str中是否包含字符searchChar。
如果str为null或”“，返回false；
如果searchChar不在str中，返回false。
28.public static boolean contains(String str, String searchStr)
判断字符串str是否包含字符串searchStr。
如果str为null或searchStr为null，返回false；
如果str为”“，并且searchStr为”“，返回true
举例：
StringUtils.contains(“”, “”) = true
StringUtils.contains(“dfg”, “”) = true
StringUtils.contains(“dfg”, “d”) = true
StringUtils.contains(“dfg”, “gz”) = false
29.public static boolean containsIgnoreCase(String str, String searchStr)
判断字符串str是否包含字符串searchStr，不区分大小写。
和28类似。
30.public static int indexOfAny(String str, char[] searchChars)
找出字符数组searchChars中的字符第一次出现在字符串str中的位置。
如果字符数组中的字符都不在字符串中，则返回-1
如果字符串为null或”“，则返回-1
举例(*表示任意)：
StringUtils.indexOfAny(null, *) = -1
StringUtils.indexOfAny(“”, *) = -1
StringUtils.indexOfAny(*, []) = -1
StringUtils.indexOfAny(“asdf”, [‘a’,’f’,’ ‘]) = 0
StringUtils.indexOfAny(“bs df”, [‘a’,’f’,’ ‘]) = 2
StringUtils.indexOfAny(“bsdf”, [‘a’,’f’,’ ‘]) = 3
StringUtils.indexOfAny(“bbeegg”, [‘a’,’f’,’ ‘]) = -1
31.public static int indexOfAny(String str, String searchChars)
找出字符串searchChars中的字符第一次出现在字符串str中的位置。
如果字符串searchChars中的字符都不在字符串str中，则返回-1
如果searchChars或str为null或为”“，则返回-1
举例(*表示任意)：
StringUtils.indexOfAny(null, *) = -1
StringUtils.indexOfAny(“”, *) = -1
StringUtils.indexOfAny(*, null) = -1
StringUtils.indexOfAny(*, “”) = -1
StringUtils.indexOfAny(“asdf”, “af “) = 0
StringUtils.indexOfAny(“bs df”, “af “) = 2
StringUtils.indexOfAny(“bsdf”, “af “) = 3
StringUtils.indexOfAny(“bbeegg”, “af “) = -1
32.public static int indexOfAnyBut(String str, char[] searchChars)
找出字符串str中不在字符数组searchChars中的第一个字符的位置。
如果字符串中的所有字符都在字符数组中，则返回-1
如果字符串为null或”“，则返回-1
举例(*表示任意)：
StringUtils.indexOfAnyBut(null, *) = -1
StringUtils.indexOfAnyBut(“”, *) = -1
StringUtils.indexOfAnyBut(*, []) = -1
StringUtils.indexOfAnyBut(“asdf”, [‘a’,’f’,’ ‘]) = 1
StringUtils.indexOfAnyBut(“bs df”, [‘a’,’f’,’ ‘]) = 0
StringUtils.indexOfAnyBut(” aaf”, [‘a’,’f’,’ ‘]) = -1
StringUtils.indexOfAnyBut(“bbeegg”, [‘a’,’f’,’ ‘]) = 0
33.public static int indexOfAnyBut(String str, String searchChars)
找出字符串str中不在字符串searchChars中的第一个字符的位置。
如果字符串str中的所有字符都在字符串searchChars中，则返回-1
如果字符串str或searchChars为null或”“，则返回-1
举例(*表示任意)：
StringUtils.indexOfAnyBut(null, *) = -1
StringUtils.indexOfAnyBut(“”, *) = -1
StringUtils.indexOfAnyBut(*, null) = -1
StringUtils.indexOfAnyBut(*, “”) = -1
StringUtils.indexOfAnyBut(“asdf”, “af “) = 1
StringUtils.indexOfAnyBut(“bs df”, “af “) = 0
StringUtils.indexOfAnyBut(” aaf”, “af “) = -1
StringUtils.indexOfAnyBut(“bbeegg”, “af “) = 0
34.public static boolean containsOnly(String str, char[] valid)
判断是否字符串str仅包含字符数组valid中的字符，即字符串中的字符是否都在字符数组中。
如果str为null，则返回false；如果str为”“，则返回true
举例(*表示任意)：
StringUtils.containsOnly(null, *)) = false
StringUtils.containsOnly(“”, *)) = true
StringUtils.containsOnly(“afaf”, [‘a’,’f’,’ ‘]))= true
StringUtils.containsOnly(“af a”, [‘a’,’f’,’ ‘]))= true
StringUtils.containsOnly(“a”, [‘a’,’f’,’ ‘])) = true
StringUtils.containsOnly(“afg”, [‘a’,’f’,’ ‘])) = false
StringUtils.containsOnly(“bbeegg”, [])) = false
35.public static boolean containsOnly(String str, String validChars)
判断是否字符串str仅包含字符串validChars中的字符，
即字符串str中的字符是否都在字符串validChars中。
和34类似，举例(*表示任意)：
StringUtils.containsOnly(null, *) = false
StringUtils.containsOnly(*, null) = false
StringUtils.containsOnly(“”, “”) = true
StringUtils.containsOnly(“”, “a”) = true
StringUtils.containsOnly(“afaf”, “af “) = true
StringUtils.containsOnly(“af a”, “af “) = true
StringUtils.containsOnly(“afg”, “af “) = false
StringUtils.containsOnly(“afg”, “”) = false
36.public static boolean containsNone(String str, char[] invalidChars)
判断是否字符串str不包含字符数组invalidChars中的字符，如果含有则返回false。
举例(*表示任意)：
StringUtils.containsNone(null, *) = true
StringUtils.containsNone(*, []) = true
StringUtils.containsNone(“”, *) = true
StringUtils.containsNone(“ab”, []) = true
StringUtils.containsNone(“b”, [‘a’,’f’,’ ‘]) = true
StringUtils.containsNone(“bcd”, [‘a’,’f’,’ ‘]) = true
StringUtils.containsNone(“abc”, [‘a’,’f’,’ ‘]) = false
StringUtils.containsNone(” “, [‘a’,’f’,’ ‘]) = false
37.public static boolean containsNone(String str, String invalidChars)
判断是否字符串str不包含字符串invalidChars中的字符，如果含有则返回false。
举例(*表示任意)：
StringUtils.containsNone(null, *) = true
StringUtils.containsNone(*, null) = true
StringUtils.containsNone(“”, *) = true
StringUtils.containsNone(“ab”, “”) = true
StringUtils.containsNone(“b”, “af “) = true
StringUtils.containsNone(“bcd”, “af “) = true
StringUtils.containsNone(“abc”, “af “) = false
StringUtils.containsNone(” “, “af “) = false

38.public static int indexOfAny(String str, String[] searchStrs)
找出字符串数组searchStrs中的字符串第一次出现在字符串str中的位置。
如果数组中没有字符串在str中，则返回-1
如果数组为null或长度为0，则返回-1
举例(*表示任意)：
StringUtils.indexOfAny(null, *) = -1
StringUtils.indexOfAny(*, null) = -1
StringUtils.indexOfAny(*, []) = -1
StringUtils.indexOfAny(“”, [“”]) = 0
StringUtils.indexOfAny(“bbeegg”, [“as”,”df”,”yy”]) = -1
StringUtils.indexOfAny(“asdfgh”, [“as”,”df”,”yy”]) = 0
StringUtils.indexOfAny(“dfasgh”, [“as”,”df”,”yy”]) = 0
StringUtils.indexOfAny(“ghasdf”, [“as”,”df”,”yy”]) = 2
39.public static int lastIndexOfAny(String str, String[] searchStrs)
找出字符串数组searchStrs中的字符串最后一次出现在字符串str中的位置。
如果数组中没有字符串在str中，则返回-1
如果数组为null或长度为0，则返回-1
举例(*表示任意)：
StringUtils.lastIndexOfAny(null, *) = -1
StringUtils.lastIndexOfAny(*, null) = -1
StringUtils.lastIndexOfAny(*, []) = -1
StringUtils.lastIndexOfAny(“”, [“”]) = 0
StringUtils.lastIndexOfAny(“bbeegg”, [“as”,”df”,”yy”]) = -1
StringUtils.lastIndexOfAny(“asdfgh”, [“as”,”df”,”yy”]) = 2
StringUtils.lastIndexOfAny(“dfghjk”, [“as”,”df”,”yy”]) = 0
StringUtils.lastIndexOfAny(“ghasdf”, [“as”,”df”,”yy”]) = 4
StringUtils.lastIndexOfAny(“ghasdf”, [“as”,”df”,”“]) = 6
40.public static String substring(String str, int start)
得到字符串str的子串。
如果start小于0，位置是从后往前数的第|start|个
如果str为null或”“，则返回它本身
举例(*表示任意)：
StringUtils.substring(null, *) = null
StringUtils.substring(“”, *) = “”
StringUtils.substring(“asdf”, 0)) = “asdf”
StringUtils.substring(“asdf”, 1)) = “sdf”
StringUtils.substring(“asdf”, 3)) = “f”
StringUtils.substring(“asdf”,) = “”
StringUtils.substring(“asdf”, -1)) = “f”
StringUtils.substring(“asdf”, -3)) = “sdf”
StringUtils.substring(“asdf”, -8)) = “asdf”
41.public static String substring(String str, int start, int end)
得到字符串str的子串。
如果start小于0，位置是从后往前数的第|start|个，
如果end小于0，位置是从后往前数的第|end|个，
如果str为null或”“，则返回它本身
举例(*表示任意)：
StringUtils.substring(null, , ) = null
StringUtils.substring(“”, * , *) = “”;
StringUtils.substring(“asdf”, 0, 2) = “as”
StringUtils.substring(“asdf”, 0, -1) = “asd”
StringUtils.substring(“asdf”, 2, -1) = “d”
StringUtils.substring(“asdf”, 2, -2) = “”
StringUtils.substring(“asdf”, 3, 2) = “”
StringUtils.substring(“asdf”, 1, = “sdf”
StringUtils.substring(“asdf”, -1, -3) = “”
StringUtils.substring(“asdf”, -3, -1) = “sd”
StringUtils.substring(“asdf”, -8, 5) = “asdf”
42.public static String left(String str, int len)
得到字符串str从左边数len长度的子串。
如果str为null或为”“，则返回它本身
如果len小于0，则返回””
举例(*表示任意)：
StringUtils.left(null, *) = null
StringUtils.left(*, -ve) = “”
StringUtils.left(“”, *) = “”
StringUtils.left(“asdf”, 0) = “”
StringUtils.left(“asdf”, 2) = “as”
StringUtils.left(“asdf”, = “asdf”
43.public static String right(String str, int len)
得到字符串str从右边数len长度的子串。
如果str为null或为”“，则返回它本身
如果len小于0，则返回””
举例(*表示任意)：
StringUtils.right(null, *) = null
StringUtils.right(*, -ve) = “”
StringUtils.right(“”, *) = “”
StringUtils.right(“asdf”, 0) = “”
StringUtils.right(“asdf”, 2) = “df”
StringUtils.right(“asdf”, = “asdf”
44.public static String mid(String str, int pos, int len)
得到字符串str从pos开始len长度的子串。
如果str为null或为”“，则返回它本身
如果len小于0或pos大于srt的长度，则返回””
如果pos小于0，则pos设为0
举例(*表示任意)：
StringUtils.mid(null, , ) = null
StringUtils.mid(“”, , ) = “”
StringUtils.mid(, , -ve) = “”
StringUtils.mid(“asdf”, 0, 4)) = “asdf”
StringUtils.mid(“asdf”, 2, 2)) = “df”
StringUtils.mid(“asdf”, 2, 5)) = “df”
StringUtils.mid(“asdf”, -2, 1)) = “a”
StringUtils.mid(“asdf”, 0, -1)) = “”
45.public static String substringBefore(String str, String separator)
得到字符串str的在字符串separator出现前的字串，且separator不包括在内。
如果str为null或为”“，则返回它本身
如果separator为null，则返回str本身
举例(*表示任意)：
StringUtils.substringBefore(null, *) = null
StringUtils.substringBefore(“”, *) = “”
StringUtils.substringBefore(“asdfg”, null)) = “asdfg”
StringUtils.substringBefore(“asdfg”, “a”)) = “”
StringUtils.substringBefore(“asdfg”, “sd”)) = “a”
StringUtils.substringBefore(“asdfsag”, “sa”)) = “asdf”
StringUtils.substringBefore(“asdfg”, “h”)) = “asdfg”
StringUtils.substringBefore(“asdfg”, “”)) = “”
StringUtils.substringBefore(“asdfg”, “dfgh”)) = “asdfg”
StringUtils.substringBefore(“asdfg”, “dfg”)) = “as”
StringUtils.substringBefore(“abbbabbba”, “bb”)) = “a”
46.public static String substringAfter(String str, String separator)
得到字符串str的在字符串separator出现后的字串，且separator不包括在内。
如果str为null或为”“，则返回它本身
如果separator为null，则返回””
举例(*表示任意)：
StringUtils.substringAfter(null, *) = null
StringUtils.substringAfter(“”, *) = “”
StringUtils.substringAfter(*, null) = “”
StringUtils.substringAfter(“asdfg”, “a”)) = “sdfg”
StringUtils.substringAfter(“asdfg”, “sd”)) = “fg”
StringUtils.substringAfter(“asdfsag”, “sa”)) = “g”
StringUtils.substringAfter(“asdfg”, “h”)) = “”
StringUtils.substringAfter(“asdfg”, “”)) = “asdfg”
StringUtils.substringAfter(“asdfg”, “dfgh”)) = “”
StringUtils.substringAfter(“asdfg”, “dfg”)) = “”
StringUtils.substringAfter(“abbbabbba”, “bb”))= “babbba”
47.public static String substringBeforeLast(String str, String separator)
和45类似，得到字符串str的在字符串separator最后一次出现前的字串。
这里不再举例。
48.public static String substringAfterLast(String str, String separator)
和46类似，得到字符串str的在字符串separator最后一次出现后的字串。
这里不再举例。
49.public static String substringBetween(String str, String tag)
得到str中的在两个字符串tag中间的字符串，即str中的tag所夹的串。
如果str为null或tag为null，返回null
举例(*表示任意)：
StringUtils.substringBetween(null, *) = null
StringUtils.substringBetween(*, null) = null
StringUtils.substringBetween(“”, “”) = “”
StringUtils.substringBetween(“”, “a”)) = null
StringUtils.substringBetween(“asdfdf”, “df”)) = “”
StringUtils.substringBetween(“asdfas”, “as”)) = “df”
StringUtils.substringBetween(“dfasdfasdfas”, “df”)) = “as”
StringUtils.substringBetween(“dfasdfasdfas”, “as”)) = “df”
StringUtils.substringBetween(“dfasdfasgdf”, “df”)) = “as”
50.public static String substringBetween(String str, String open, String close)
得到str中的在两个字符串open和close中间的字符串，即open和close所夹的串。
如果str为null或open为null或close为null，返回null
举例(*表示任意)：
StringUtils.substringBetween(null, , ) = null
StringUtils.substringBetween(, null, ) = null
StringUtils.substringBetween(, , null) = null
StringUtils.substringBetween(“”, “”, “”) = “”
StringUtils.substringBetween(“”, “”, “]”) = null
StringUtils.substringBetween(“”, “[“, “]”) = null
StringUtils.substringBetween(“[]”, “[“,”]”)) = “”
StringUtils.substringBetween(“a[sd]f”, “[“,”]”)) = “sd”
StringUtils.substringBetween(“a[sd]f[gh]”, “[“,”]”)) = “sd”
StringUtils.substringBetween(“a[sd]f”, “]”,”[“)) = null
StringUtils.substringBetween(“a[sd]f”, “”,”“)) = “”
51.public static String[] substringsBetween(String str, String open, String close)
得到str中的在两个字符串open和close中间的字符串，即open和close所夹的串，
把所有符合的结果放在数组中返回。
和50类似，但是返回了所有的结果(50只返回了第一个匹配的结果)。
这里不再举例。

52.public static String[] split(String str)
把字符串拆分成一个字符串数组，用空白符(whitespace)作为分隔符。
Whitespace是这样定义的 {@link Character#isWhitespace(char)}
如果字符串为null，返回null
如果字符串为”“，返回空数组{}
举例(*表示任意)：
StringUtils.split(null) = null
StringUtils.split(“”) = {}
StringUtils.split(“as df yy”)) = {“as”,”df”,”yy”}
StringUtils.split(” as df yy “)) = {“as”,”df”,”yy”}
StringUtils.split(“as\ndf\ryy”)) = {“as”,”df”,”yy”}
StringUtils.split(“as\tdf\fyy”)) = {“as”,”df”,”yy”}
StringUtils.split(“as df \fyy”)) = {“as”,”df”,”yy”}
StringUtils.split(“as\t \r df \f \n yy”)) = {“as”,”df”,”yy”}
StringUtils.split(“as”)) = {“as”}
StringUtils.split(” as “)) = {“as”}
53.public static String[] split(String str, char separatorChar)
把字符串拆分成一个字符串数组，用指定的字符separatorChar作为分隔符。
如果字符串为null，返回null
如果字符串为”“，返回空数组{}
举例(*表示任意)：
StringUtils.split(null, *) = null
StringUtils.split(“”, *) = {}
StringUtils.split(“as df yy”,’ ‘)) = {“as”,”df”,”yy”}
StringUtils.split(” as df yy “,’ ‘)) = {“as”,”df”,”yy”}
StringUtils.split(“asodfoyy”,’o’)) = {“as”,”df”,”yy”}
StringUtils.split(“as.df.yy”,’.’)) = {“as”,”df”,”yy”}
StringUtils.split(“as\ndf\nyy”,’\n’))= {“as”,”df”,”yy”}
StringUtils.split(“as”,’ ‘)) = {“as”}
StringUtils.split(” as “,’ ‘)) = {“as”}
54.public static String[] split(String str, String separatorChars)
把字符串拆分成一个字符串数组，用指定的字符串separatorChars作为分隔符。
如果字符串str为null，返回null
如果字符串str为”“，返回空数组{}
如果separatorChars为null，则默认为空白符
和53类似。
举例(*表示任意)：
StringUtils.split(“as \rdf \t yy”,null)) = {“as”,”df”,”yy”}
StringUtils.split(“as\ndf\fyy”,null)) = {“as”,”df”,”yy”}
StringUtils.split(“as”,”“)) = {“as”}
55.public static String[] split(String str, String separatorChars, int max)
把字符串拆分成一个字符串数组，用指定的字符串separatorChars作为分隔符，
数组的最大长度为max。
如果字符串str为null，返回null
如果字符串str为”“，返回空数组{}
如果separatorChars为null，则默认为空白符
如果max小于等于0，认为是没有限制
举例(*表示任意)：
StringUtils.split(null, , ) = null
StringUtils.split(“”, , ) = {}
StringUtils.split(“as df yy”,null,0)) = {“as”,”df”,”yy”}
StringUtils.split(“as df yy”,” “,0)) = {“as”,”df”,”yy”}
StringUtils.split(“as.df.yy”,”.”,-1)) = {“as”,”df”,”yy”}
StringUtils.split(“as.df.yy”,”.”,4)) = {“as”,”df”,”yy”}
StringUtils.split(“as-!-df-!-yy”,”-!-“,0)) = {“as”,”df”,”yy”}
StringUtils.split(“as.df.yy”,”.”,2)) = {“as”,”df.yy”}
StringUtils.split(“as”,”“,0)) = {“as”}
StringUtils.split(“as”,”“,2)) = {“as”}
56.public static String[] splitByWholeSeparator(String str, String separator)
个人认为和54功能一样。区别有待发掘。
57.public static String[] splitByWholeSeparator( String str, String separator, int max )
个人认为和55功能一样。区别有待发掘。
58.public static String[] splitPreserveAllTokens(String str)
把字符串str拆分成一个数组，用空白符(whitespace)作为分隔符，保留所有的标识，
包括相邻分隔符产生的空的标识。它可作为StringTokenizer的一个替代。
Whitespace是这样定义的{@link Character#isWhitespace(char)}。
举例(*表示任意)：
StringUtils.splitPreserveAllTokens(null)) = null
StringUtils.splitPreserveAllTokens(“”)) = {}
StringUtils.splitPreserveAllTokens(“as df gh jk”)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as\ndf\rgh\fjk”)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as\tdf gh jk”)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as df gh”)) = {“as”,”“,”df”,”gh”}
StringUtils.splitPreserveAllTokens(” as df “)) = {“”,”as”,”“,”“,”df”,”“,”“}
59.public static String[] splitPreserveAllTokens(String str, char separatorChar)
和58类似，只是分隔符为字符separatorChar。
举例(*表示任意)：
StringUtils.splitPreserveAllTokens(null, *) = null
StringUtils.splitPreserveAllTokens(“”, *) = {}
StringUtils.splitPreserveAllTokens(“as df gh jk”,’ ‘)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as.df.gh.jk”,’.’)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as..df.gh”,’.’)) = {“as”,”“,”df”,”gh”}
StringUtils.splitPreserveAllTokens(“,as,,,df,,”,’,’)) = {“”,”as”,”“,”“,”df”,”“,”“}
StringUtils.splitPreserveAllTokens(“as.df.gh”,’,’)) = {“as.df.gh”}
60.public static String[] splitPreserveAllTokens(String str, String separatorChars)
和59类似，只是分隔符为字符串separatorChars。
举例(*表示任意)：
StringUtils.splitPreserveAllTokens(null, *) = null
StringUtils.splitPreserveAllTokens(“”, *) = {}
StringUtils.splitPreserveAllTokens(“as df gh jk”,null)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as\ndf\rgh\fjk”,null))= {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as df gh jk”,” “)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as.df.gh.jk”,”.”)) = {“as”,”df”,”gh”,”jk”}
StringUtils.splitPreserveAllTokens(“as..df.gh”,”.”)) = {“as”,”“,”df”,”gh”}
StringUtils.splitPreserveAllTokens(“,as,,,df,,”,”,”)) = {“”,”as”,”“,”“,”df”,”“,”“}
StringUtils.splitPreserveAllTokens(“as.df.gh”,”,”)) = {“as.df.gh”}
StringUtils.splitPreserveAllTokens(“as.df.gh”,”“)) = {“as.df.gh”}
61.public static String[] splitPreserveAllTokens(String str, String separatorChars, int max)
和上面几个类似，只是指定了数组的最大长度。
如果max为0或负数，则认为没有限制。
这里不再举例。
62.public static String join(Object[] array)
把数组中的元素连接成一个字符串返回。
举例(*表示任意)：
StringUtils.join(null) = null
StringUtils.join({}) = “”
StringUtils.join({“as”,”df”,”gh”,”jk”})) = “asdfghjk”
StringUtils.join({“as”,”“,”df”,”gh”})) = “asdfgh”
StringUtils.join({“”,”as”,”“,”“,”df”,”“,”“})) = “asdf”
63.public static String join(Object[] array, char separator)
把数组中的元素连接成一个字符串返回，把分隔符separator也加上。
举例(*表示任意)：
StringUtils.join(null, *) = null
StringUtils.join({}, *) = “”
StringUtils.join({null}, *) = “”
StringUtils.join({“as”,”df”,”gh”,”jk”},’ ‘)) = “as df gh jk”
StringUtils.join({“as”,”df”,”gh”,”jk”},’.’)) = “as.df.gh.jk”
StringUtils.join({“as”,”“,”df”,”gh”},’.’)) = “as..df.gh”
StringUtils.join({“”,”as”,”“,”“,”df”,”“,”“},’,’))= “,as,,,df,,”
StringUtils.join({“”,”as”,”“,”“,”df”,”“,”“},’ ‘))= ” as df ”
StringUtils.join({“as.df.gh”},’.’)) = “as.df.gh”
StringUtils.join({“as.df.gh”},’ ‘)) = “as.df.gh”
64.public static String join(Object[] array, char separator, int startIndex, int endIndex)
把数组中的元素连接成一个字符串返回，把分隔符separator也加上。
连接的开始位置为startIndex，结束位置为endIndex。
这里不再举例。
65.public static String join(Object[] array, String separator)
与63类似，这里不再举例。
66.public static String join(Object[] array, String separator, int startIndex, int endIndex)
与64类似，这里不再举例。

67.public static String deleteWhitespace(String str)
删除字符串中的所有空白符(whitespace)，空白符是这样定义的{@link Character#isWhitespace(char)}。
举例(*表示任意)：
StringUtils.deleteWhitespace(null) = null
StringUtils.deleteWhitespace(“”) = “”
StringUtils.deleteWhitespace(“asd”)) = “asd”,
StringUtils.deleteWhitespace(“as df”)) = “asdf”
StringUtils.deleteWhitespace(“as\n\r\f\tdf”)) = “asdf”
StringUtils.deleteWhitespace(“as\bdf”)) = “as\bdf”
StringUtils.deleteWhitespace(” as df “)) = “asdf”
68.public static String removeStart(String str, String remove)
如果字符串str是以字符串remove开始，则去掉这个开始，然后返回，否则返回原来的串。
举例(*表示任意)：
StringUtils.removeStart(null, *) = null
StringUtils.removeStart(“”, *) = “”
StringUtils.removeStart(, null) =
StringUtils.removeStart(“asdf”,”“)) = “asdf”
StringUtils.removeStart(“asdf”,”as”)) = “df”
StringUtils.removeStart(“asdf”,”df”)) = “asdf”
StringUtils.removeStart(“asdf”,”gh”)) = “asdf”
69.public static String removeEnd(String str, String remove)
如果字符串str是以字符串remove结尾，则去掉这个结尾，然后返回，否则返回原来的串。
这里不再举例。
70.public static String remove(String str, String remove)
去掉字符串str中所有包含remove的部分，然后返回。
这里不再举例。
71.public static String remove(String str, char remove)
去掉字符串str中所有包含remove的部分，然后返回。
这里不再举例。
72.public static String replaceOnce(String text, String repl, String with)
在字符串text中用with代替repl，仅一次。
这里不再举例。
73.public static String replace(String text, String repl, String with)
在字符串text中用with代替repl，替换所有。
这里不再举例。
74.public static String replace(String text, String repl, String with, int max)
在字符串text中用with代替repl，max为最大替换次数。
如果max小于0，则替换所有。
这里不再举例。
75. public static String replaceChars(String str, char searchChar, char replaceChar)
在字符串str中用字符replaceChar代替所有字符searchChar，
如果字符串为null或”“，则返回它本身。
这里不再举例。
76.public static String replaceChars(String str, String searchChars, String replaceChars)
用replaceChars代替str中的searchChars。
replaceChars的长度应该和searchChars的长度相等，
如果replaceChars的长度大于searchChars的长度，超过长度的字符将被忽略，
如果replaceChars的长度小于searchChars的长度，超过长度的字符将被删除。
举例(*表示任意)：
StringUtils.replaceChars(null, , ) = null
StringUtils.replaceChars(“”, , ) = “”
StringUtils.replaceChars(“asdf”, null, *) = “asdf”
StringUtils.replaceChars(“asdf”, “”, *) = “asdf”
StringUtils.replaceChars(“asdf”,”s”,null)) = “adf”
StringUtils.replaceChars(“asdf”,”s”,”“)) = “adf”
StringUtils.replaceChars(“asdsfsg”,”s”,”y”)) = “aydyfyg”
StringUtils.replaceChars(“asdf”,”sd”,”yy”)) = “ayyf”
StringUtils.replaceChars(“asdf”,”sd”,”yyy”)) = “ayyf”
StringUtils.replaceChars(“asssdf”,”s”,”yyy”)) = “ayyydf”
StringUtils.replaceChars(“asdf”,”sd”,”y”)) = “ayf”
StringUtils.replaceChars(“assssddddf”,”sd”,”y”))= “ayyyyf”
77.public static String overlay(String str, String overlay, int start, int end)
用字符串overlay覆盖字符串str从start到end之间的串。
如果str为null，则返回null
如果start或end小于0，则设为0
如果start大于end，则两者交换
如果start或end大于str的长度，则认为等于str的长度
举例(*表示任意)：
StringUtils.overlay(null, , , *) = null
StringUtils.overlay(“”,”as”,0,0)) = “as”
StringUtils.overlay(“asdfgh”,”qq”,2,5)) = “asqqh”
StringUtils.overlay(“asdfgh”,”qq”,5,2)) = “asqqh”
StringUtils.overlay(“asdfgh”,”qq”,-1,3)) = “qqfgh”
StringUtils.overlay(“asdfgh”,”qq”,-1,-3)) = “qqasdfgh”
StringUtils.overlay(“asdfgh”,”qq”,7,10)) = “asdfghqq”
StringUtils.overlay(“asdfgh”,”qq”,0,8)) = “qq”
StringUtils.overlay(“asdfgh”,”qq”,2,8)) = “asqq”
StringUtils.overlay(“asdfgh”,null,2,5)) = “ash”
StringUtils.overlay(“asdfgh”,”“,2,5)) = “ash”
78.public static String chop(String str)
去掉字符串str的最后一个字符。
如果字符串以”\r\n”结尾，则去掉它们。
这里不再举例。
79.public static String repeat(String str, int repeat)
重复字符串repeat次，组合成一个新串返回。
如果字符串str为null或”“，则返回它本身
如果repeat小于0，则返回””
举例(*表示任意)：
StringUtils.repeat(null, *) = null
StringUtils.repeat(“”, *) = “”
StringUtils.repeat(“a”, 3) = “aaa”
StringUtils.repeat(“ab”, 2) = “abab”
StringUtils.repeat(“a”, -2) = “”
80.public static String rightPad(String str, int size)
如果str为null，则返回null
如果字符串长度小于size，则在右边补空格使其长度等于size，然后返回
如果字符串长度大于等于size，则返回它本身
这里不再举例。
81.public static String rightPad(String str, int size, char padChar)
和80类似，只是补的字符为padChar。
这里不再举例。
82.public static String rightPad(String str, int size, String padStr)
和80类似，只是补的是字符串padStr。
举例(*表示任意)：
StringUtils.rightPad(null, , ) = null
StringUtils.rightPad(“”,0,”“)) = “”
StringUtils.rightPad(“”,3,”“)) = ” ”
StringUtils.rightPad(“”,3,”a”)) = “aaa”
StringUtils.rightPad(“”,2,”as”)) = “as”
StringUtils.rightPad(“as”,-1,”df”)) = “as”
StringUtils.rightPad(“as”,0,”df”)) = “as”
StringUtils.rightPad(“as”,3,”df”)) = “asd”
StringUtils.rightPad(“as”,8,”df”)) = “asdfdfdf”
StringUtils.rightPad(“as”,5,null)) = “as ”
StringUtils.rightPad(“as”,5,”“)) = “as ”
83.public static String leftPad(String str, int size)
和80类似，只是补左边。
这里不再举例。
84.public static String leftPad(String str, int size, char padChar)
和81类似。
这里不再举例。
85.public static String leftPad(String str, int size, String padStr)
和82类似。
这里不再举例。
86.public static String center(String str, int size)
产生一个字符串返回，该字符串长度等于size，str位于新串的中心，其他位置补空格。
如果str为null，则返回null
如果size小于str的长度，则返回str本身
举例(*表示任意)：
StringUtils.center(null, *) = null
StringUtils.center(“”,1)) = ” ”
StringUtils.center(“”,2)) = ” ”
StringUtils.center(“as”,-1)) = “as”
StringUtils.center(“as”,2)) = “as”
StringUtils.center(“as”,3)) = “as ”
StringUtils.center(“as”,4)) = ” as ”
StringUtils.center(“as”,10)) = ” as ”
87.public static String center(String str, int size, char padChar)
和86类似，只是其他位置补padChar。
这里不再举例。
88.public static String center(String str, int size, String padStr)
和86类似，只是其他位置补padStr。
这里不再举例。
89.public static String swapCase(String str)
把字符串中的字符大写转换为小写，小写转换为大写。
举例：
StringUtils.swapCase(null) = null
StringUtils.swapCase(“”) = “”
StringUtils.swapCase(“Hello Boys”)) = “hELLO bOYS”
StringUtils.swapCase(“I am 11”)) = “i AM 11”
90.public static int countMatches(String str, String sub)
计算字符串sub在字符串str中出现的次数。
如果str为null或”“，则返回0
举例(*表示任意)：
StringUtils.countMatches(null, *) = 0
StringUtils.countMatches(“”, *) = 0
StringUtils.countMatches(“asdf”,”as”)) = 1
StringUtils.countMatches(“asdfas”,”as”)) = 2
StringUtils.countMatches(“dfgh”,”as”)) = 0
StringUtils.countMatches(“as”,”“)) = 0
StringUtils.countMatches(“as”,null)) = 0




    ==========================================
    * */
}
