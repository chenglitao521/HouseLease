package com.xiamo.weixin.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiamo.common.utils.BeanFactoryUtil;
import com.xiamo.weixin.dao.IWeinXinDao;
import com.xiamo.weixin.po.SNSUserInfo;
import com.xiamo.weixin.po.WeixinOauth2Token;
import com.xiamo.weixin.po.WeixinUserInfo;
import com.xiamo.weixin.po.menu.Button;
import com.xiamo.weixin.po.menu.CommonButton;
import com.xiamo.weixin.po.menu.ComplexButton;
import com.xiamo.weixin.po.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * <dl>
 * <dt>WeiXinUtil</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/24 0024</dd>
 * </dl>
 *
 * @author chenglitao
 */

@Component
public class WeiXinUtil {

    private static Logger logger = LoggerFactory.getLogger(WeiXinUtil.class);

    // 获取access_token接口
    public static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //创建菜单接口
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //获取用户信息接口
    public static String getUserInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //获得jsapi ticket
    public static String getJsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";


    //获取网页access_Token
    public static String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    //检验授权凭证（access_token）是否有效
    public static String check_url = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
    //刷新授权凭证
    public static String refresh_accesstoken_url="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;


    @Autowired
    IWeinXinDao weiXinDaoImpl;

    public static String jsapi_ticket;
    public static String access_token;

    static {

    }

    private void initInstance() {

        if (weiXinDaoImpl == null) {
            weiXinDaoImpl = (IWeinXinDao) BeanFactoryUtil.getBean("weiXinDaoImpl");
        }
    }


    /**
     * 发送https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();

            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
            logger.error("连接超时：{}", ce);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("https请求异常：{}", e);
        }
        return jsonObject;
    }


    /**
     * @date:2018/3/6 0006 15:14
     * @className:WeiXinUtil
     * @author:chenglitao
     * @description:检验授权凭证（access_token）是否有效
     */
    public static Boolean checkAccessToken(String access_token, String appId) {
        String requestUrl = check_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", appId);

        JSONObject jsonObject1 = httpsRequest(requestUrl, "GET", null);
        if (jsonObject1 != null) {
            if (0 != jsonObject1.getIntValue("errcode")) {

                logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject1.getIntValue("errcode"), jsonObject1.getString("errmsg"));
                return false;
            }
        }

        return true;
    }

    /**
     * @date:2018/3/6 0006 15:28
     * @className:WeiXinUtil
     * @author:chenglitao
     * @description:刷新AccessToken
     *
     */
    public static WeixinOauth2Token refreshAccessToken(String refreshToken, String appId) {
        String requestUrl = refresh_accesstoken_url.replace("REFRESH_TOKEN", refreshToken).replace("APPID", appId);
        WeixinOauth2Token wat = new WeixinOauth2Token();
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            int errorCode = jsonObject.getIntValue("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (errorCode != 0) {

                logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }

            try {

                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getIntValue("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                errorCode = jsonObject.getIntValue("errcode");
                errorMsg = jsonObject.getString("errmsg");
                logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }

    /**
     * 自动刷新获得access_token
     * 每隔一个小时55分钟获取一次
     */
    // @Scheduled(fixedRate = 1000 * 60 * 115)
    public void getAccessToken() {
        initInstance();
        String requestUrl = token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {

                access_token = jsonObject.getString("access_token");

/*
                if(token!=null){
                    weiXinDaoImpl.updateAccessToken(jsonObject.getString("access_token"), jsonObject.getString("expires_in"));
                }else{
                    weiXinDaoImpl.insertAccessToken(jsonObject.getString("access_token"), jsonObject.getString("expires_in"));
                }
*/

                logger.debug("access_token：{}", jsonObject.getString("access_token"));
            } catch (JSONException e) {
                e.printStackTrace();
                // 获取token失败
                logger.error("自动获取token失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("自动获取token失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }

        //获得jsapi_ticket
        String requestUrl1 = getJsapi_ticket_url.replace("ACCESS_TOKEN", access_token);
        JSONObject jsonObject1 = httpsRequest(requestUrl1, "GET", null);
        if (null != jsonObject1) {

            try {

                jsapi_ticket = jsonObject1.getString("ticket");
                logger.debug("jsapi_ticket：{}", jsonObject1.getString("ticket"));
            } catch (JSONException e) {
                e.printStackTrace();
                // 获取token失败
                logger.error("自动获取jsapi_ticket失败 errcode:{} errmsg:{}", jsonObject1.getIntValue("errcode"), jsonObject1.getString("errmsg"));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("自动获取jsapi_ticket失败 errcode:{} errmsg:{}", jsonObject1.getIntValue("errcode"), jsonObject1.getString("errmsg"));
            }

        }

    }

    /**
     * 生成JS-SDK签名
     *
     * @param
     * @param url
     * @return
     */
    public static Map<String, String> sign(String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        logger.info("string1 : " + string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8")); //对string1 字符串进行SHA-1加密处理
            signature = byteToHex(crypt.digest());  //对加密后字符串转成16进制
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }


    /**
     * @date:2018/1/25 0025 15:41
     * @className:WeiXinUtil
     * @author:chenglitao
     * @description:创建自定义菜单
     */
    public static int createMenu(String accessToken) {

        Menu menu = getMenu();
        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串

        String jsonMenu = JSONObject.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = WeiXinUtil.httpsRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    /**
     * @date:2018/1/25 0025 15:41
     * @className:WeiXinUtil
     * @author:chenglitao
     * @description:获取用户信息
     */
    public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
        WeixinUserInfo weixinUserInfo = null;
        // 拼接请求地址

        getUserInfo_url = getUserInfo_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = httpsRequest(getUserInfo_url, "GET", null);

        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getIntValue("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getIntValue("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    logger.error("用户{}已取消关注", weixinUserInfo.getOpenId());
                } else {
                    int errorCode = jsonObject.getIntValue("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                }
            }
        }

        return weixinUserInfo;
    }


    /**
     * @date:2018/1/25 0025 16:22
     * @className:WeiXinUtil
     * @author:chenglitao
     * @description: 获取网页授权凭证
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        // 拼接请求地址

        access_token_url = access_token_url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);

        // 获取网页授权凭证
        JSONObject jsonObject = httpsRequest(access_token_url, "GET", null);
        if (null != jsonObject) {
            int errorCode = jsonObject.getIntValue("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (errorCode != 0) {

                logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }

            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getIntValue("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                errorCode = jsonObject.getIntValue("errcode");
                errorMsg = jsonObject.getString("errmsg");
                logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getIntValue("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                String js = JSONObject.toJSONString(jsonObject.getJSONArray("privilege"), SerializerFeature.WriteClassName);//将array数组转换成字符串
                List<String> collection = JSONObject.parseArray(js, String.class);//把字符串转换成集合
                snsUserInfo.setPrivilegeList(collection);
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getIntValue("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }

    /**
     * @date:2018/1/25 0025 16:40
     * @className:WeiXinUtil
     * @author:chenglitao
     * @description:URL编码（utf-8）
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("文章1");
        btn11.setType("view");
        btn11.setKey("1");

        String urrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx49bbc90ad4a7cc59&redirect_uri=http%3A%2F%2F197e68v051.iask" +
                ".in%2Fweixin%2FOAuthAfter&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        btn11.setUrl(urrl);

        CommonButton btn12 = new CommonButton();
        btn12.setName("文章2");
        btn12.setType("click");
        btn12.setKey("我爱你");

        CommonButton btn13 = new CommonButton();
        btn13.setName("文章3");
        btn13.setType("click");
        btn13.setKey("我爱你");


        CommonButton btn21 = new CommonButton();
        btn21.setName("我要救援");
        btn21.setType("click");
        btn21.setKey("我爱你");

        CommonButton btn22 = new CommonButton();
        btn22.setName("生活缴费");
        btn22.setType("click");
        btn22.setKey("我爱你");


        CommonButton btn31 = new CommonButton();
        btn31.setName("商铺查看");
        btn31.setType("view");
        btn31.setKey("31");
        btn31.setUrl("https://www.baidu.com/");

        CommonButton btn32 = new CommonButton();
        btn32.setName("我的房产");
        btn32.setType("view");
        btn32.setKey("32");
        btn32.setUrl("https://www.baidu.com/");

        CommonButton btn33 = new CommonButton();
        btn33.setName("在线客服");
        btn33.setType("click");
        btn33.setKey("我爱你");


        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("信息中心");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[]{btn11, btn12, btn13});


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("生活服务");
        mainBtn2.setSub_button(new CommonButton[]{btn21, btn22});


        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("个人中心");
        mainBtn3.setSub_button(new CommonButton[]{btn31, btn32, btn33});


        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    //生成随机字符串
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    //生成时间戳字符串
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
