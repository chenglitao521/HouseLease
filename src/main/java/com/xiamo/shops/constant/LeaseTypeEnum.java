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
 * 租赁类型
 *
 * @author CLT
 */
public enum LeaseTypeEnum {
    MONTH("0", "月"),
    YEAR("1", "年"),
    ELSE("", "");
    public String value;
    public String desc;

    LeaseTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static LeaseTypeEnum getEnum(String value) {
        if (value != null)
            for (LeaseTypeEnum e : values()) {
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
