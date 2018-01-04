package com.xiamo.user.controller;

import com.xiamo.common.vo.AjaxResultPo;
import com.xiamo.common.vo.PageInfo;
import com.xiamo.user.po.UserPo;
import com.xiamo.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <dl>
 * <dt>UserController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2017/12/25 0025</dd>
 * </dl>
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
    public AjaxResultPo query(int page, int rows, UserPo po) {
        logger.info("进入查询用户的方法！");
        AjaxResultPo res = new AjaxResultPo();
        try {
            PageInfo pageInfo = null;
            if (page > 0) {
                pageInfo = new PageInfo((page - 1) * rows, rows);
            }

            List<UserPo> list = userServiceImpl.query(po, pageInfo);
            if (page > 0) {
                res.setTotal(pageInfo.getTotalRecords());
            } else {
                res.setTotal(list.size());
            }

            res.setRows(list);
        } catch (Exception e) {
            res.setMessage("查询用户信息失败");
        }
        return res;
    }
}
