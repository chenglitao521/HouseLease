package com.xiamo.common.utils;


import java.io.File;

public class ConfigUtils {

    public static String UPLOAD_FILE_PATH() {
        String s = WebappConfigUtil.getParameter("UPLOAD_FILE_PATH");

        File file = new File(s);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }
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
