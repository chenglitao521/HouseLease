package com.xiamo.common.utils;

/**
 * <dl>
 * <dt>StringUtils</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/3/24 0024</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class StringUtil {
    /**
     * 将数组转换成以逗号分隔的字符串
     *
     * @param needChange
     *            需要转换的数组
     * @return 以逗号分割的字符串
     */
    public static String arrayToStrWithComma(String[] needChange) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < needChange.length; i++) {
            sb.append(needChange[i]);
            if ((i + 1) != needChange.length) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
