package com.xiamo.privilege.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>UserType</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 * 用户类别
 *
 * @author CLT
 */
public enum UserType {
    CIVILAFFAIRS("1", "民政局"),
    POLICE("2", "公安"),
    LANDLORD("3", "房东"),
    TENANT("4", "租客"),
    USER("5", "用户"),
    ELSE("", "未知");
    public String value;
    public String desc;

    UserType(String value, String desc) {
        this.value = value;
        this.desc = desc;

    }

    public static UserType getEnum(String value) {
        if (value != null)
            for (UserType e : values()) {
                if (StringUtils.equalsIgnoreCase(e.value, value)) {
                    return e;
                }
            }
        return ELSE;
    }
}
