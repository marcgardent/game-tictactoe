package com.ludopant.agonist.util;

public class StringUtil {

    public static String outline(String target, int start) {
        return StringUtil.outline(target, start, target.length());
    }

    public static String outline(String target, int start, int end) {
        String head = target.substring(0, start);
        String body = target.substring(start, end);
        String tail = target.substring(end);
        return  String.format("%s<ERROR>%s</ERROR>%s", head, body, tail);
    }
}
