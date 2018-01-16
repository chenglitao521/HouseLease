package com.xiamo.weixin.controller;

import com.xiamo.weixin.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * <dl>
 * <dt>CommonController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/14 0014</dd>
 * </dl>
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {
    private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @RequestMapping(value = "/zxingdecode", method = RequestMethod.GET)
    public ModelAndView zxingdecode(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView ret = new ModelAndView();
        try {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");

            System.out.println(signature);
            System.out.println(timestamp);
            System.out.println(nonce);
            System.out.println(echostr);


            PrintWriter out = response.getWriter();

            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret.setViewName("zxingcoder");
        return ret;
    }


}
