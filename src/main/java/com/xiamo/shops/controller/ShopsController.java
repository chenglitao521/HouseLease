package com.xiamo.shops.controller;

import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.user.constant.UserStatus;
import com.xiamo.user.po.UserPo;
import com.xiamo.user.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <dl>
 * <dt>UserController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2017/12/25 0025</dd>
 * </dl>
 *  商铺接口
 * @author chenglitao
 */
@Controller
@RequestMapping("/shops")
public class ShopsController {
    private static final Logger logger = LoggerFactory.getLogger(ShopsController.class);

    @Autowired
    IUserService userServiceImpl;

    @ResponseBody
    @RequestMapping("/index")
    public String index(String loginName, String password, Model model) {
        logger.info("进入LoginController.index方法，loginName={}，password={}", loginName, password);
        AjaxResultPo res = new AjaxResultPo(true,"操作成功");
        try {

            String MD5Password = DigestUtils.md5Hex(password.trim());
            UserPo userPo = userServiceImpl.loginByName(loginName);

            if (userPo == null) {
                model.addAttribute("errMsg", "用户名或密码错误!");
            }

            if (!userPo.getPassword().equals(MD5Password)) {
                model.addAttribute("errMsg", "用户名或密码错误!");
            }

            if (userPo.getUserType().equals(UserStatus.STOP.value)) {

                model.addAttribute("errMsg", "用户已注销!");
            }
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage("查询用户信息失败");
        }
        return "login";
    }
}
