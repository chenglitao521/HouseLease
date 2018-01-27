package com.xiamo.weixin.controller;

import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.weixin.po.SNSUserInfo;
import com.xiamo.weixin.po.WeixinOauth2Token;
import com.xiamo.weixin.service.IWeiXinService;
import com.xiamo.weixin.utils.SignUtil;
import com.xiamo.weixin.utils.WeiXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * <dl>
 * <dt>CommonController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/14 0014</dd>
 * </dl>
 * 微信服务器调用
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {
    @Autowired
    IWeiXinService weiXinServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;
    /**
     * @date:2018/1/17 0017 16:41
     * @className:WeixinController
     * @author:chenglitao
     * @description:验证消息来自微信服务器
     */

    @RequestMapping(method = RequestMethod.GET)
    public void zxingdecode(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");

            logger.debug("微信加密签名：{}", signature);
            logger.debug("微信时间戳：{}", timestamp);
            logger.debug("微信随机数：{}", nonce);
            logger.debug("微信随机字符串：{}", echostr);

            PrintWriter out = response.getWriter();

            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
            out.close();
            out = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @date:2018/1/17 0017 16:44
     * @className:WeixinController
     * @author:chenglitao
     * @description:处理微信发来的消息
     */
    @RequestMapping(method = RequestMethod.POST)
    public void handlemessge(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 消息的接收、处理、响应
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            // 调用核心业务类接收消息、处理消息
            String respXml = weiXinServiceImpl.processRequest(request);

            // 响应消息
            PrintWriter out = response.getWriter();
            out.print(respXml);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/createMenu")
    public AjaxResultPo createMenu(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("进入WeixinController.createMenu");
        try {
            int re = weiXinServiceImpl.createMenu();
            logger.debug("菜单创建成功！！！！！！！！！！！！！！！！！！！！！");
            return AjaxResultPo.success("创建成功", 1, re);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultPo.successDefault();
    }

    /**
     * @date:2018/1/25 0025 16:36
     * @className:WeixinController
     * @author:chenglitao
     * @description:授权后回调
     */
    @ResponseBody
    @RequestMapping(value = "/OAuthAfter")
    public ModelAndView OAuthAfter(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("进入WeixinController.OAuthAfter");

        ModelAndView mv = new ModelAndView();
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String requestUrl = request.getRequestURL().toString();
            // 用户同意授权后，能获取到code
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            logger.debug("code=" + code);
            logger.debug("state=" + state);
            // 用户同意授权
            if (!"authdeny".equals(code)) {
                // 获取网页授权access_token
                WeixinOauth2Token weixinOauth2Token = WeiXinUtil.getOauth2AccessToken(appId, appSecret, code);
                // 网页授权接口访问凭证
                String accessToken = weixinOauth2Token.getAccessToken();
                // 用户标识
                String openId = weixinOauth2Token.getOpenId();
                // 获取用户信息
                SNSUserInfo snsUserInfo = WeiXinUtil.getSNSUserInfo(accessToken, openId);

                // 设置要传递的参数

                mv.addObject("snsUserInfo", JsonUtils.toJson(snsUserInfo));
                mv.addObject("state", state);
            }
            String url =  "http://" + request.getServerName()+ request.getRequestURI()+"?"+request.getQueryString();
            Map<String, String> sign = WeiXinUtil.sign(url);

            mv.addObject("timestamp",sign.get("timestamp"));
            mv.addObject("nonceStr",sign.get("nonceStr"));
            mv.addObject("signature",sign.get("signature"));
            mv.addObject("appId",appId);

            mv.setViewName("/index");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }


}
