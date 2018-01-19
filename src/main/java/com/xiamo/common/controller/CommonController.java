package com.xiamo.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <dl>
 * <dt>CommonController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>CreateDate: 2018/1/14 0014</dd>
 * </dl>
 *
 * @author chenglitao
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping(value = "/uploadImage")
    public ModelAndView zxingdecode(HttpServletRequest request) {
        ModelAndView ret = new ModelAndView();




        ret.setViewName("zxingcoder");

        return ret;
    }


}
