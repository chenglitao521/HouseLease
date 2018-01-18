package com.xiamo.privilege.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <dl>
 * <dt>UserStatus</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/6</dd>
 * </dl>
 * 用户状态
 * @author CLT
 */
public enum UserStatus
{
    USING("1", "正常"),
    STOP("0", "注销"),
    ELSE("","未知");
    public String value;
    public String desc;

    UserStatus(String value, String desc){
        this.value = value;
        this.desc = desc;
    }
    public static UserStatus getEnum(String value){
        if (value!=null)
            for(UserStatus e:values()){
                if(StringUtils.equalsIgnoreCase(e.value, value)){
                    return e;
                }
            }
        return ELSE;
    }
}
