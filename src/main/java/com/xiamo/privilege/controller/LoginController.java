package com.xiamo.privilege.controller;

import com.xiamo.common.po.ServiceException;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.privilege.constant.UserStatus;
import com.xiamo.privilege.po.UserPo;
import com.xiamo.privilege.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 登陆接口
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService userServiceImpl;

    @ResponseBody
    @RequestMapping("/index")
    public AjaxResultPo index(String loginName, String password) {
        logger.info("进入LoginController.index方法，loginName={}，password={}", loginName, password);
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {

            String MD5Password = DigestUtils.md5Hex(password.trim());
            UserPo userPo = userServiceImpl.loginByName(loginName);

            if (userPo == null) {
                throw new ServiceException("用户名或密码错误!");

            }

            if (!userPo.getPassword().equals(MD5Password)) {
                throw new ServiceException("用户名或密码错误!");
            }

            if (UserStatus.STOP.value.equals(userPo.getUserType())) {
                throw new ServiceException("用户已注销!");

            }
        } catch (ServiceException e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage("查询用户信息失败");
        }
        return res;
    }
}
