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
 *
 *电类型
 * @author CLT
 */
public enum ElectricTypeEnum {
    CIVIL("0", "民用电"),
    COMMERCIAL("1", "商用电"),
    ELSE("", "");
    public String value;
    public String desc;

    ElectricTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ElectricTypeEnum getEnum(String value) {
        if (value != null)
            for (ElectricTypeEnum e : values()) {
                if (StringUtils.equalsIgnoreCase(e.value, value)) {
                    return e;
                }
            }
        return ELSE;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
