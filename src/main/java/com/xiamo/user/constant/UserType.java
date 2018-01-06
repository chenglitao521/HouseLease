package com.xiamo.user.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>UserType</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 *
 * @author CLT
 */
public enum UserType
{


    UNICOM("1", "运营商" ),

    ELSE("","未知" );
    public String value;
    public String desc;

    UserType(String value, String desc){
        this.value = value;
        this.desc = desc;

    }
    public static UserType getEnum(String value){
        if (value!=null)
            for(UserType e:values()){
                if(StringUtils.equalsIgnoreCase(e.value, value)){
                    return e;
                }
            }
        return ELSE;
    }
}
