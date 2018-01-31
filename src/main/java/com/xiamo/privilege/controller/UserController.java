package com.xiamo.privilege.controller;

import com.xiamo.common.utils.JsonUtils;
import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.privilege.po.UserPo;
import com.xiamo.privilege.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * <dl>
 * <dt>UserController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2017/12/25 0025</dd>
 * </dl>
 * 用户管理
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userServiceImpl;

    //映射一个action
    @RequestMapping("/index")
    public String index() {
        //输出日志文件
        logger.info("the first jsp pages");
        //返回一个index.jsp这个视图
        return "user/list";
    }

    @ResponseBody
    @RequestMapping("/query")
    public AjaxResultPo query(Integer page, Integer rows, UserPo userPo) {
        logger.info("进入查询用户的方法！");
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            PageInfo pageInfo = null;
            if (page > 0) {
                pageInfo = new PageInfo((page - 1) * rows, rows);
            }

            List<UserPo> list = userServiceImpl.query(userPo, pageInfo);
            if (page > 0) {
                res.setTotal(pageInfo.getTotalRecords());
            } else {
                res.setTotal(list.size());
            }

            res.setRows(list);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage("查询用户信息失败");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping("/add")
    public AjaxResultPo add(UserPo userPo) throws IOException {
        logger.info("进入添加用户的方法！{}", JsonUtils.toJson(userPo));
        AjaxResultPo res = new AjaxResultPo(true, "操作成功");
        try {
            String MD5Password = DigestUtils.md5Hex(userPo.getPassword().trim());
            userPo.setPassword(MD5Password);
            int r = userServiceImpl.add(userPo);
            res.setRows(r);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage("添加用户信息失败");
            return AjaxResultPo.failure("添加用户信息失败");
        }
        return res;
    }
}
