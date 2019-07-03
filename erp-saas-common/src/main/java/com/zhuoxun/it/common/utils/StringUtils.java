package com.zhuoxun.it.common.utils;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * <p>
 * String 工具类
 * </p>
 *
 * @author liwen
 */
public class StringUtils {

    /**
     * 安全的进行字符串 format
     *
     * @param target
     *            目标字符串
     * @param params
     *            format 参数
     * @return format 后的
     */
    public static String format(String target, Object... params) {
        if (target.contains("%s") && ArrayUtils.isNotEmpty(params)) {
            return String.format(target, params);
        }
        return target;
    }

    /**
     * Blob 转为 String 格式
     *
     * @param blob
     *            Blob 对象
     * @return 转换后的
     */
    public static String blob2String(Blob blob) {
        if (null != blob) {
            try {
                byte[] returnValue = blob.getBytes(1, (int)blob.length());
                return new String(returnValue, StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw ExceptionUtils.app("Blob Convert To String Error!");
            }
        }
        return null;
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs
     *            需要判断字符串
     * @return 判断结果
     */
    public static boolean isEmpty(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param cs
     *            需要判断字符串
     * @return 判断结果
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断字符串是否为纯大写字母
     *
     * @param str
     *            要匹配的字符串
     * @return
     */
    public static boolean isUpperCase(String str) {
        return matches("^[A-Z]+$", str);
    }

    /**
     * 正则表达式匹配
     *
     * @param regex
     *            正则表达式字符串
     * @param input
     *            要匹配的字符串
     * @return 如果 input 符合 regex 正则表达式格式, 返回true, 否则返回 false;
     */
    public static boolean matches(String regex, String input) {
        if (null == regex || null == input) {
            return false;
        }
        return Pattern.matches(regex, input);
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     *            ignore
     * @return ignore
     */
    public static boolean checkValNotNull(Object object) {
        if (object instanceof CharSequence) {
            return isNotEmpty((CharSequence)object);
        }
        return object != null;
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     *            ignore
     * @return ignore
     */
    public static boolean checkValNull(Object object) {
        return !checkValNotNull(object);
    }

    /**
     * 包含大写字母
     *
     * @param word
     *            待判断字符串
     * @return ignore
     */
    public static boolean containsUpperCase(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为驼峰下划线混合命名
     *
     * @param word
     *            待判断字符串
     * @return ignore
     */
    public static boolean isMixedMode(String word) {
        return matches(".*[A-Z]+.*", word) && matches(".*[/_]+.*", word);
    }

    /**
     * Check if a String ends with a specified suffix.
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
     * The comparison is case sensitive.
     * </p>
     * <p>
     * 
     * <pre>
     * StringUtils.endsWith(null, null)      = true
     * StringUtils.endsWith(null, "abcdef")  = false
     * StringUtils.endsWith("def", null)     = false
     * StringUtils.endsWith("def", "abcdef") = true
     * StringUtils.endsWith("def", "ABCDEF") = false
     * </pre>
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param suffix
     *            the suffix to find, may be null
     * @return <code>true</code> if the String ends with the suffix, case sensitive, or both <code>null</code>
     * @see String#endsWith(String)
     * @since 2.4
     */
    public static boolean endsWith(String str, String suffix) {
        return endsWith(str, suffix, false);
    }

    /**
     * Case insensitive check if a String ends with a specified suffix.
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
     * The comparison is case insensitive.
     * </p>
     * <p>
     * 
     * <pre>
     * StringUtils.endsWithIgnoreCase(null, null)      = true
     * StringUtils.endsWithIgnoreCase(null, "abcdef")  = false
     * StringUtils.endsWithIgnoreCase("def", null)     = false
     * StringUtils.endsWithIgnoreCase("def", "abcdef") = true
     * StringUtils.endsWithIgnoreCase("def", "ABCDEF") = false
     * </pre>
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @param suffix
     *            the suffix to find, may be null
     * @return <code>true</code> if the String ends with the suffix, case insensitive, or both <code>null</code>
     * @see String#endsWith(String)
     * @since 2.4
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return endsWith(str, suffix, true);
    }

    /**
     * Check if a String ends with a specified suffix (optionally case insensitive).
     *
     * @param str
     *            the String to check, may be null
     * @param suffix
     *            the suffix to find, may be null
     * @param ignoreCase
     *            inidicates whether the compare should ignore case (case insensitive) or not.
     * @return <code>true</code> if the String starts with the prefix or both <code>null</code>
     * @see String#endsWith(String)
     */
    private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (str == null || suffix == null) {
            return (str == null && suffix == null);
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        int strOffset = str.length() - suffix.length();
        return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
    }

    /**
     * Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
     * more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separatorChars splits on whitespace.
     * </p>
     * <p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("abc  def", " ") = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     * </p>
     *
     * @param str
     *            the String to parse, may be null
     * @param separatorChars
     *            the characters used as the delimiters, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String input
     */
    public static String[] split(final String str, final String separatorChars) {
        List<String> strings = splitWorker(str, separatorChars, -1, false);
        return strings.toArray(new String[0]);
    }

    /**
     * Performs the logic for the {@code split} and {@code splitPreserveAllTokens} methods that return a maximum array
     * length.
     *
     * @param str
     *            the String to parse, may be {@code null}
     * @param separatorChars
     *            the separate character
     * @param max
     *            the maximum number of elements to include in the array. A zero or negative value implies no limit.
     * @param preserveAllTokens
     *            if {@code true}, adjacent separators are treated as empty token separators; if {@code false}, adjacent
     *            separators are treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     */
    public static List<String> splitWorker(final String str, final String separatorChars, final int max,
        final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return Collections.emptyList();
        }
        final List<String> list = new ArrayList<>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list;
    }

    /**
     * 是否为CharSequence类型
     *
     * @param clazz
     *            class
     * @return true 为是 CharSequence 类型
     */
    public static boolean isCharSequence(Class<?> clazz) {
        return clazz != null && CharSequence.class.isAssignableFrom(clazz);
    }

    /**
     * 是否为Boolean类型(包含普通类型)
     *
     * @param propertyCls
     *            ignore
     * @return ignore
     */
    public static boolean isBoolean(Class<?> propertyCls) {
        return propertyCls != null
            && (boolean.class.isAssignableFrom(propertyCls) || Boolean.class.isAssignableFrom(propertyCls));
    }

    /**
     * 第一个首字母小写，之后字符大小写的不变
     * <p>
     * StringUtils.firstCharToLower( "UserService" ) = userService
     * </p>
     * <p>
     * StringUtils.firstCharToLower( "UserServiceImpl" ) = userServiceImpl
     * </p>
     *
     * @param rawString
     *            需要处理的字符串
     * @return ignore
     */
    public static String firstCharToLower(String rawString) {
        return prefixToLower(rawString, 1);
    }

    /**
     * 前n个首字母小写,之后字符大小写的不变
     *
     * @param rawString
     *            需要处理的字符串
     * @param index
     *            多少个字符(从左至右)
     * @return ignore
     */
    public static String prefixToLower(String rawString, int index) {
        String beforeChar = rawString.substring(0, index).toLowerCase();
        String afterChar = rawString.substring(index);
        return beforeChar + afterChar;
    }

    /**
     * 删除字符前缀之后,首字母小写,之后字符大小写的不变
     * <p>
     * StringUtils.removePrefixAfterPrefixToLower( "isUser", 2 ) = user
     * </p>
     * <p>
     * StringUtils.removePrefixAfterPrefixToLower( "isUserInfo", 2 ) = userInfo
     * </p>
     *
     * @param rawString
     *            需要处理的字符串
     * @param index
     *            删除多少个字符(从左至右)
     * @return ignore
     */
    public static String removePrefixAfterPrefixToLower(String rawString, int index) {
        return prefixToLower(rawString.substring(index), 1);
    }

    public static boolean containsLowerCase(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成随机字符串 包含字母和数字 首位必须是字母
     * 
     * @param length 自定义长度
     * @return
     */
    public static String getRandomString(int length) {
        // 生成随机[a-z]字符串，包含大小写
        StringBuffer userName = new StringBuffer(RandomStringUtils.randomAlphabetic(1));
        // 随机生成数字
        userName.append(RandomStringUtils.randomNumeric(1));
        // 生成指定长度的字母和数字的随机组合字符串,可能全是字母或数字
        userName.append(RandomStringUtils.randomAlphanumeric(length - 2));
        return userName.toString();
    }
    
    
    /**
     * 获取当前用户IP地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
        String ip = "";
        try{
            ip = request.getHeader("x-forwarded-for");
            if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-P");
            }
            if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-P");
            }
            if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }
            ip = "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
        }catch(Exception e){
            ip = "127.0.0.1";
        }
        return ip;
    }

}
