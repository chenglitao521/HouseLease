package com.xiamo.weixin.utils;

import com.xiamo.weixin.po.Token;

/**
 * <dl>
 * <dt>TokenTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/24 0024</dd>
 * </dl>
 *
 * @author chenglitao
 */
public class TokenTest {
    public static void main(String[] args) throws Exception {
        //修改appID，secret
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx38c623b92c52b793&secret=ee39cb1c014e2b9e8136366510e13586";
        Token token= AccessTokenUtil.getToken("wx38c623b92c52b793","ee39cb1c014e2b9e8136366510e13586");
        //输出返回结果
        System.out.println(token.getAccessToken());
    }
}
