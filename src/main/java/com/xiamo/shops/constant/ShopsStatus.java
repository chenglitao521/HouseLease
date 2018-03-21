package com.xiamo.shops.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>UserStatus</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 *  商铺状态
 * @author CLT
 */
public enum ShopsStatus
{
    ALREADYRENTED("0", "待出租"),
    TOBERENTED("1", "已出租"),
    TOBESOLD("2","待出售"),
    ELSE("3","其他");
    public String value;
    public String desc;

    ShopsStatus(String value, String desc){
        this.value = value;
        this.desc = desc;
    }
    public static ShopsStatus getEnum(String value){
        if (value!=null)
            for(ShopsStatus e:values()){
                if(StringUtils.equalsIgnoreCase(e.value, value)){
                    return e;
                }
            }
        return ELSE;
    }
}
