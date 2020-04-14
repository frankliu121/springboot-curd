package lf.ssm.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname ToolUtil
 * @Date 2020/4/14 19:46
 * @Created by liufeng
 */
public class ToolUtil {


    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /** 下划线转驼峰 */
    public static String[] lineToHump(String[] strArr) {
        ArrayList<String> list = new ArrayList<>(strArr.length);
        for (String str : strArr){
            list.add(lineToHump(str));
        }
        return list.toArray(new String[strArr.length]);
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 驼峰转下划线 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String[] humpToLine(String[] strArr) {
        ArrayList<String> list = new ArrayList<>(strArr.length);
        for (String str : strArr){
            list.add(humpToLine(str));
        }
        return list.toArray(new String[strArr.length]);
    }


    public static void main(String[] args) {
        String lineToHump = lineToHump("f_parent_no_leader");
        System.out.println(lineToHump);// fParentNoLeader
        System.out.println(humpToLine(lineToHump));// f_parent_no_leader
    }
}
