package com.xiamo.shops.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>ShopsAttrEnum</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/29</dd>
 * </dl>
 *  房子属性
 * @author CLT
 */
public enum ShopsAttrEnum {
    OWN("0", "自己住房"),
    LEASE("1", "出租房"),
    ELSE("", "");
    public String value;
    public String desc;

    ShopsAttrEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ShopsAttrEnum getEnum(String value) {
        if (value != null)
            for (ShopsAttrEnum e : values()) {
                if (StringUtils.equalsIgnoreCase(e.value, value)) {
                    return e;
                }
            }
        return ELSE;
    }
}
