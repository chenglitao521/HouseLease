package com.xiamo.common.utils;


public class ConfigUtils {

    public static String UPLOAD_FILE_PATH() {
        String s = WebappConfigUtil.getParameter("UPLOAD_FILE_PATH");
        if (!s.endsWith("/")) {
            s = s + "/";
        }
        return s;
    }

    public static String VIRTUAL_PATH() {
        String s = WebappConfigUtil.getParameter("VIRTUAL_PATH");
        if (!s.endsWith("/")) {
            s = s + "/";
        }
        return s;
    }
}
