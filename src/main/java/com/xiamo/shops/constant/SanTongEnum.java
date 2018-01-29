package com.xiamo.shops.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>SanTongEnum</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/29</dd>
 * </dl>
 * 三通
 *
 * @author CLT
 */
public enum SanTongEnum {
    WATER("0", "水"),
    ELECTRIC("1", "电"),
    GAS("2", "气"),
    ELSE("", "");
    public String value;
    public String desc;

    SanTongEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static SanTongEnum getEnum(String value) {
        if (value != null)
            for (SanTongEnum e : values()) {
                if (StringUtils.equalsIgnoreCase(e.value, value)) {
                    return e;
                }
            }
        return ELSE;
    }
}
