package com.xiamo.merchant.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>UserStatus</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 *  商家状态
 * @author CLT
 */
public enum MerchantStatus
{
    USING("1", "正常"),
    STOP("0", "暂停"),
    ELSE("","未知");
    public String value;
    public String desc;

    MerchantStatus(String value, String desc){
        this.value = value;
        this.desc = desc;
    }
    public static MerchantStatus getEnum(String value){
        if (value!=null)
            for(MerchantStatus e:values()){
                if(StringUtils.equalsIgnoreCase(e.value, value)){
                    return e;
                }
            }
        return ELSE;
    }
}
