package com.xiamo.shops.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>ShopsStructureEnum</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/29</dd>
 * </dl>
 *
 * @author CLT
 */
public enum ShopsStructureEnum {
    OWN("0", "框架"),
    LEASE("1", "实体墙"),
    ELSE("", "");
    public String value;
    public String desc;

    ShopsStructureEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ShopsStructureEnum getEnum(String value) {
        if (value != null)
            for (ShopsStructureEnum e : values()) {
                if (StringUtils.equalsIgnoreCase(e.value, value)) {
                    return e;
                }
            }
        return ELSE;
    }
}
