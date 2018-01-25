package com.xiamo.weixin.utils;

import com.xiamo.weixin.po.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger logger = LoggerFactory.getLogger(TokenTest.class);

    public static void main(String[] args) throws Exception {
      /*  //修改appID，secret
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx38c623b92c52b793&secret=ee39cb1c014e2b9e8136366510e13586";
        Token token= WeiXinUtil.getToken("wx38c623b92c52b793","ee39cb1c014e2b9e8136366510e13586");
        //输出返回结果
        System.out.println(token.getAccessToken());*/

        // 第三方用户唯一凭证wx38c623b92c52b793
        String appId = "wx49bbc90ad4a7cc59";
        // 第三方用户唯一凭证密钥ee39cb1c014e2b9e8136366510e13586
        String appSecret = "dc0237e21bcb3fa78ef6fa94de932bac";


        String url = WeiXinUtil.urlEncodeUTF8("http://hlm1234.duapp.com/weixin/OAuthAfter");
        System.out.println(url);
        // 调用接口获取access_token
       Token at = WeiXinUtil.getAccessToken(appId, appSecret);
        if (null != at) {
            // 调用接口创建菜单
            int result = WeiXinUtil.createMenu(at.getAccessToken());

            // 判断菜单创建结果
            if (0 == result)
                logger.info("菜单创建成功！");
            else
                logger.info("菜单创建失败，错误码：" + result);
        }
    }


}
